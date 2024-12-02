-- Drop existing tables if they exist (useful for resetting the database)
DROP TABLE IF EXISTS exam_results CASCADE;
DROP TABLE IF EXISTS subjects CASCADE;
DROP TABLE IF EXISTS students CASCADE;

-- Create the students table
CREATE TABLE students (
        student_id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        surname VARCHAR(100) NOT NULL,
        date_of_birth DATE NOT NULL,
        phone_number VARCHAR(15),
        primary_skill VARCHAR(100),
        created_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        UNIQUE (name, surname, date_of_birth)  -- Ensuring no duplicate students
);

-- Create the subjects table
CREATE TABLE subjects (
        subject_id SERIAL PRIMARY KEY,
        subject_name VARCHAR(100) NOT NULL,
        tutor VARCHAR(100) NOT NULL,
        created_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        UNIQUE (subject_name)  -- Ensuring no duplicate subjects
);

-- Create the exam_results table
CREATE TABLE exam_results (
        result_id SERIAL PRIMARY KEY,
        student_id INT NOT NULL,
        subject_id INT NOT NULL,
        mark DECIMAL(5, 2) CHECK (mark >= 0 AND mark <= 100),
        created_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
        FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE
);

-- Optional: Insert some initial data into students
INSERT INTO students (name, surname, date_of_birth, phone_number, primary_skill)
VALUES
    ('Name1', 'Surname1', '1990-01-01', '1234567890', 'Java Programming'),
    ('Name2', 'Surname2', '1990-01-02', '0987654321', 'Data Science');

-- Optional: Insert some initial data into subjects
INSERT INTO subjects (subject_name, tutor)
VALUES
    ('Computer Science', 'Dr. Smith'),
    ('Mathematics', 'Prof. Johnson');

-- Optional: Insert some initial exam results
INSERT INTO exam_results (student_id, subject_id, mark)
VALUES
    (1, 1, 85.50),
    (1, 2, 90.00),
    (2, 1, 78.00);

-- Create indexes for performance improvement
CREATE INDEX idx_students_name ON students(name);
CREATE INDEX idx_students_surname ON students(surname);
CREATE INDEX idx_subjects_name ON subjects(subject_name);
CREATE INDEX idx_exam_results_student ON exam_results(student_id);
CREATE INDEX idx_exam_results_subject ON exam_results(subject_id);