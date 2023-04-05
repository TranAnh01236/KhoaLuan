--Search Exam By Lesson
select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at
from exams e
inner join exam_details d on
e.id = d.exam_id
inner join questions q on q.id = d.question_id
where q.lesson_id = 'LES0007'
group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at

--Search Exam By Chapter
select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at
from exams e
inner join exam_details d on
e.id = d.exam_id
inner join questions q on q.id = d.question_id
inner join lessons l on l.id = q.lesson_id
where l.chapter_id = 'CHA0001'
group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at

--Serach Exam by Subject
select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at
from exams e
inner join exam_details d on
e.id = d.exam_id
inner join questions q on q.id = d.question_id
inner join lessons l on l.id = q.lesson_id
inner join chapters c on c.id = l.chapter_id
where c.subject_id = 'SUB0001'
group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at

-- Search Exams By Grade
select e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at
from exams e
inner join exam_details d on
e.id = d.exam_id
inner join questions q on q.id = d.question_id
inner join lessons l on l.id = q.lesson_id
inner join chapters c on c.id = l.chapter_id
inner join subjects s on s.id = c.subject_id
where s.grade_id = 'GRA0001'
group by e.id, e.name, e.description, e.duration, e.image, e.type, e.created_at, e.updated_at