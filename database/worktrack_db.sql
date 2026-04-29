CREATE DATABASE IF NOT EXISTS worktrack_db;
USE worktrack_db;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS jobs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    pay_per_hour DOUBLE NOT NULL,
    work_timing VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    posted_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS applications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL,
    applied_at DATE NOT NULL,
    CONSTRAINT fk_application_student FOREIGN KEY (student_id) REFERENCES users(id),
    CONSTRAINT fk_application_job FOREIGN KEY (job_id) REFERENCES jobs(id)
);

CREATE TABLE IF NOT EXISTS work_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    hours_worked DOUBLE NOT NULL,
    work_date DATE NOT NULL,
    CONSTRAINT fk_worklog_student FOREIGN KEY (student_id) REFERENCES users(id),
    CONSTRAINT fk_worklog_job FOREIGN KEY (job_id) REFERENCES jobs(id)
);

CREATE TABLE IF NOT EXISTS finalized_work_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    student_name VARCHAR(255) NOT NULL,
    job_title VARCHAR(255) NOT NULL,
    hours_worked DOUBLE NOT NULL,
    finalized_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS feedback (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    admin_id BIGINT NOT NULL,
    rating INT NOT NULL,
    comment TEXT NOT NULL,
    created_at DATE NOT NULL,
    CONSTRAINT fk_feedback_student FOREIGN KEY (student_id) REFERENCES users(id),
    CONSTRAINT fk_feedback_job FOREIGN KEY (job_id) REFERENCES jobs(id),
    CONSTRAINT fk_feedback_admin FOREIGN KEY (admin_id) REFERENCES users(id)
);
