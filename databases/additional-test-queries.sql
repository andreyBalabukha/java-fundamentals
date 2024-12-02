-- Trigger to Update updated_datetime on Student Update

CREATE OR REPLACE FUNCTION update_student_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_datetime := CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_student
    BEFORE UPDATE ON students
    FOR EACH ROW
    EXECUTE FUNCTION update_student_timestamp();


-- Validation to Reject Student Names with Special Characters

ALTER TABLE students
    ADD CONSTRAINT chk_student_name
        CHECK (name !~ '[@#$]' AND surname !~ '[@#$]');

-- Creating a Snapshot Table

CREATE TABLE student_snapshots (
        snapshot_id SERIAL PRIMARY KEY,
        student_name VARCHAR(100),
        student_surname VARCHAR(100),
        subject_name VARCHAR(100),
        mark DECIMAL(5, 2),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert data into snapshot
INSERT INTO student_snapshots (student_name, student_surname, subject_name, mark)
SELECT s.name, s.surname, sub.subject_name, er.mark
FROM students s
         JOIN exam_results er ON s.student_id = er.student_id
         JOIN subjects sub ON er.subject_id = sub.subject_id;


-- Function to Return Average Mark for Input User

CREATE OR REPLACE FUNCTION avg_mark_by_student(student_name VARCHAR, student_surname VARCHAR)
RETURNS DECIMAL AS $$
DECLARE
avg_mark DECIMAL;
BEGIN
    SELECT AVG(mark) INTO avg_mark
    FROM exam_results er
    JOIN students s ON er.student_id = s.student_id
    WHERE s.name = student_name AND s.surname = student_surname;

    RETURN avg_mark;
END;
$$ LANGUAGE plpgsql;

-- Function to Return Average Mark for Input Subject Name

CREATE OR REPLACE FUNCTION avg_mark_by_subject(subject_name VARCHAR)
RETURNS DECIMAL AS $$
DECLARE
avg_mark DECIMAL;
BEGIN
    SELECT AVG(mark) INTO avg_mark
    FROM exam_results er
             JOIN subjects sub ON er.subject_id = sub.subject_id
    WHERE sub.subject_name = subject_name;

    RETURN avg_mark;
END;
$$ LANGUAGE plpgsql;

-- Function to Return Students in the "Red Zone"

CREATE OR REPLACE FUNCTION students_in_red_zone()
RETURNS TABLE(student_id INT, name VARCHAR, surname VARCHAR) AS $$
BEGIN
RETURN QUERY
SELECT s.student_id, s.name, s.surname
FROM students s
         JOIN exam_results er ON s.student_id = er.student_id
GROUP BY s.student_id
HAVING COUNT(CASE WHEN er.mark <= 3 THEN 1 END) >= 2;
END;
$$ LANGUAGE plpgsql;