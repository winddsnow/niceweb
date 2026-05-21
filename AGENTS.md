# AGENTS.md — myopencode monorepo

Multi-project monorepo. The primary project is **tool-web** (tools platform): Go/Gin backend + Vue 3/Vite frontend.

## Projects in this repo

| Directory | Stack | Status |
|-----------|-------|--------|
| `backend/` + `frontend/` | Go (Gin, JWT, PostgreSQL) + Vue 3 (Vite, Pinia) | Active |
| `tool-go/` | GoFrame v2 + Vue 3 + TS + Element Plus | Separate project, has its own `AGENTS.md` |
| `permission-demo/` | Java/Spring (Maven) | Demo project |
| `niceweb/`, `config/` | — | Empty directories |

## Backend (Go/Gin)

```bash
cd backend
go run cmd/main.go          # starts on :8080
```

- Entry: `cmd/main.go`
- Packages: `internal/handlers/`, `internal/middleware/`, `internal/auth/`, `internal/database/`, `internal/models/`, `internal/tools/`
- Config via `backend/.env` (DB_HOST, DB_PORT, DB_USER, DB_PASSWORD, DB_NAME, PORT, JWT_SECRET)
- DB schema: `backend/config/schema.sql` (auto-loaded by docker-compose)
- Passwords: bcrypt (`golang.org/x/crypto`)

## Frontend (Vue 3)

```bash
cd frontend
npm install
npm run dev                  # starts on :3000, proxies /api → :8080
npm run build                # vite build
```

- No TypeScript, no eslint config, no tests
- Router: `src/router/index.js` (auth guard via `localStorage.getItem('token')`)
- Views: `src/views/Login.vue`, `src/views/Dashboard.vue`, `src/views/TimestampTool.vue`

## Infrastructure

```bash
docker-compose up -d postgres    # PostgreSQL 15 on :5432, db=tool_web, user/pass=postgres/postgres
# Adminer UI at http://localhost:8081
```

## Seed accounts

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | admin |
| user1 | admin123 | user |

Schema inserts bcrypt hashes. These are seeded via `schema.sql` on first `docker-compose up`.

## Adding a new tool

1. Create Vue component in `frontend/src/views/`
2. Add route in `frontend/src/router/index.js`
3. Register in `frontend/src/views/Dashboard.vue` tools array
4. Add handler in `backend/internal/handlers/tools.go`
5. Register API route in `backend/cmd/main.go`

## Key gotchas

- **No tests exist** — no Go tests, no JS tests. Manual verification only.
- **No lint/typecheck** — no eslint config, no TypeScript. `npm run build` is a plain Vite build.
- **JWT secret** in `backend/.env` is `your-secret-key-change-in-production` — change for prod.
- **Auth flow**: Frontend stores JWT in localStorage, sends `Authorization: Bearer <token>`. Backend `middleware.AuthRequired()` validates.
- **CORS**: Backend `middleware.CORS()` allows all origins in dev.
- **DB init**: `schema.sql` runs on container first start. Re-running requires dropping the DB first.
- **Frontend proxy**: Vite dev server proxies `/api` to `http://localhost:8080`. API calls use relative paths (`/api/...`).
- **`tool-go/` is a different project** — uses GoFrame, MD5+Salt passwords, different seed accounts (walter/walter). Don't confuse with this project.
