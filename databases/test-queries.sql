-- a. Find user by name (exact match)
SELECT *
FROM students
WHERE name = 'Name1';

-- b. Find user by surname (partial match)
SELECT *
FROM students
WHERE surname ILIKE '%Surname%';

-- c. Find user by phone number (partial match)
SELECT *
FROM students
WHERE phone_number LIKE '%123%';

-- d. Find user with marks by user surname (partial match)
SELECT s.student_id, s.name, s.surname, er.subject_id, er.mark
FROM students s
         JOIN exam_results er ON s.student_id = er.student_id
WHERE s.surname ILIKE '%Doe%';