package com.worktrack.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>WorkTrack Backend</title>
                    <style>
                        body {
                            margin: 0;
                            font-family: Arial, sans-serif;
                            background: linear-gradient(135deg, #eef2ff 0%%, #ffffff 45%%, #ede9fe 100%%);
                            color: #0f172a;
                        }
                        .wrap {
                            max-width: 860px;
                            margin: 48px auto;
                            padding: 32px;
                        }
                        .card {
                            background: rgba(255,255,255,0.95);
                            border-radius: 24px;
                            padding: 32px;
                            box-shadow: 0 20px 55px rgba(15, 23, 42, 0.10);
                        }
                        h1 {
                            margin: 0 0 8px 0;
                            font-size: 2.5rem;
                        }
                        p {
                            line-height: 1.7;
                            color: #475569;
                        }
                        .pill {
                            display: inline-block;
                            padding: 8px 14px;
                            border-radius: 999px;
                            background: #ede9fe;
                            color: #5b21b6;
                            font-weight: 700;
                            font-size: 0.8rem;
                            letter-spacing: 0.12em;
                            text-transform: uppercase;
                        }
                        ul {
                            padding-left: 20px;
                            color: #334155;
                        }
                        a {
                            color: #4338ca;
                            font-weight: 600;
                            text-decoration: none;
                        }
                        a:hover {
                            text-decoration: underline;
                        }
                        .grid {
                            display: grid;
                            gap: 16px;
                            margin-top: 24px;
                        }
                        .box {
                            border-radius: 18px;
                            background: #f8fafc;
                            padding: 18px 20px;
                        }
                        code {
                            background: #e2e8f0;
                            padding: 2px 8px;
                            border-radius: 8px;
                        }
                    </style>
                </head>
                <body>
                    <div class="wrap">
                        <div class="card">
                            <span class="pill">WorkTrack Backend</span>
                            <h1>Backend Deployment Active</h1>
                            <p>
                                This is the deployed Spring Boot backend for the WorkTrack student work-study management system.
                                The service is running successfully and exposes REST API endpoints for authentication, jobs,
                                applications, work hours, and feedback.
                            </p>

                            <div class="grid">
                                <div class="box">
                                    <strong>Health Check:</strong>
                                    <a href="/api/health">/api/health</a>
                                </div>
                                <div class="box">
                                    <strong>Sample API Endpoint:</strong>
                                    <a href="/api/jobs">/api/jobs</a>
                                </div>
                            </div>

                            <h2>Available API Modules</h2>
                            <ul>
                                <li><code>/api/auth</code> - Login and registration</li>
                                <li><code>/api/jobs</code> - Job posting and management</li>
                                <li><code>/api/applications</code> - Student applications and approvals</li>
                                <li><code>/api/work-logs</code> - Work hour tracking</li>
                                <li><code>/api/feedback</code> - Student feedback management</li>
                            </ul>
                        </div>
                    </div>
                </body>
                </html>
                """;
    }

    @GetMapping("/api/health")
    public String health() {
        return "WorkTrack backend is running.";
    }
}
