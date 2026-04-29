# WorkTrack Full Stack Project

This project recreates the `worktrack-update.vercel.app` page flow as a Java full stack application:

- `frontend`: React + Vite
- `backend`: Spring Boot + MySQL
- `database`: SQL setup script

## Project structure

```text
New project/
  frontend/
  backend/
  database/
  README.md
```

## Reference routes matched from the Vercel app

- `/`
- `/register`
- `/login`
- `/student-dashboard`
- `/view-jobs`
- `/applied-jobs`
- `/work-hours`
- `/feedback`
- `/admin-dashboard`
- `/post-job`
- `/manage-jobs`
- `/applications-review`
- `/admin-work-hours`
- `/student-feedback`

## 1. MySQL setup

1. Open MySQL Workbench.
2. Create or open a local connection.
3. Run the SQL file:

```sql
SOURCE C:/Users/ADMIN/Documents/New project/database/worktrack_db.sql;
```

## 2. Update backend DB credentials

Open `backend/src/main/resources/application.properties` and change:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

## 3. Install Maven

`mvn` is not currently available on this machine, so install Apache Maven first.

1. Download Maven zip from https://maven.apache.org/download.cgi
2. Extract it, for example to `C:\apache-maven-3.9.9`
3. Add `C:\apache-maven-3.9.9\bin` to your `Path`
4. Open a new terminal and verify:

```powershell
mvn -version
```

## 4. Run backend

```powershell
cd "C:\Users\ADMIN\Documents\New project\backend"
mvn spring-boot:run
```

Backend runs at `http://localhost:8080`

## 5. Run frontend

```powershell
cd "C:\Users\ADMIN\Documents\New project\frontend"
npm install
npm run dev
```

Frontend runs at `http://localhost:5173`

## 6. How to use the app

1. Register one `ADMIN` account.
2. Register one `STUDENT` account.
3. Login as admin and post jobs, review applications, add work hours, and submit feedback.
4. Login as student and view jobs, apply, track hours, and read feedback.

## Optional seeded demo accounts

If the backend starts successfully, these sample accounts are auto-created:

- Admin: `admin@worktrack.com` / `admin123`
- Student: `student@worktrack.com` / `student123`

## Notes

- The reference Vercel app stores data in browser local storage. This project replaces that with a Spring Boot + MySQL backend.
- The UI style and route names were kept close to the reference app, but the data is now persistent.
