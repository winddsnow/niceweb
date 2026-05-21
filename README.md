# 工具类网页平台

前后端分离的工具类项目，前端使用Vue3.0，后端使用Go + PostgreSQL。

## 功能特性

- 用户登录（账号密码，不开放注册）
- 工具仪表板（方框网格展示工具）
- 时间戳转换工具（时间戳与日期时间相互转换）
- 可扩展添加新工具
- 身份验证保护路由

## 技术栈

### 前端
- Vue 3.0
- Vue Router
- Pinia（状态管理）
- Axios（HTTP客户端）
- Vite（构建工具）

### 后端
- Go
- Gin（Web框架）
- JWT（身份验证）
- PostgreSQL（数据库）
- bcrypt（密码哈希）

## 快速开始

### 环境要求
- Node.js 18+
- Go 1.21+
- Docker & Docker Compose（推荐）
- PostgreSQL 15（可选）

### 1. 启动数据库

使用 Docker Compose 启动 PostgreSQL：

```bash
docker-compose up -d postgres
```

数据库将运行在 `localhost:5432`，默认用户/密码为 `postgres/postgres`。

Adminer 数据库管理界面运行在 `http://localhost:8081`。

### 2. 后端设置

进入后端目录：

```bash
cd backend
```

安装 Go 依赖：

```bash
go mod download
```

运行后端：

```bash
go run cmd/main.go
```

后端将运行在 `http://localhost:8080`。

环境变量配置（在 `.env` 文件中）：
```
DB_HOST=localhost
DB_PORT=5432
DB_USER=postgres
DB_PASSWORD=postgres
DB_NAME=tool_web
PORT=8080
JWT_SECRET=your-secret-key-change-in-production
```

### 3. 前端设置

进入前端目录：

```bash
cd frontend
```

安装依赖：

```bash
npm install
```

启动开发服务器：

```bash
npm run dev
```

前端将运行在 `http://localhost:3000`。

### 4. 访问应用

打开浏览器访问 `http://localhost:3000`。

使用以下账号登录：
- 用户名：admin，密码：admin123
- 用户名：user1，密码：admin123

## 项目结构

```
├── frontend/                 # 前端项目
│   ├── src/
│   │   ├── views/           # 页面组件
│   │   ├── router/          # 路由配置
│   │   └── main.js          # 入口文件
│   ├── package.json
│   └── vite.config.js
├── backend/                  # 后端项目
│   ├── cmd/                 # 入口文件
│   ├── internal/            # 内部包
│   │   ├── auth/           # 身份验证
│   │   ├── database/       # 数据库连接
│   │   ├── handlers/       # 请求处理器
│   │   ├── middleware/     # 中间件
│   │   ├── models/         # 数据模型
│   │   └── tools/          # 工具类
│   ├── config/             # 配置文件
│   └── go.mod
├── config/                  # 共享配置
├── docker-compose.yml       # Docker配置
└── README.md
```

## API 接口

### 身份验证

#### 登录
```
POST /api/auth/login
```

请求体：
```json
{
  "username": "admin",
  "password": "admin123"
}
```

响应：
```json
{
  "token": "jwt-token",
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "admin"
  }
}
```

#### 获取当前用户信息
```
GET /api/auth/me
```
需要 Bearer Token 认证。

### 工具接口

#### 时间戳转日期
```
POST /api/tools/timestamp/to-date
```
请求体：
```json
{
  "timestamp": 1700000000
}
```

#### 日期转时间戳
```
POST /api/tools/timestamp/to-timestamp
```
请求体：
```json
{
  "date": "2023-11-15 08:13:20"
}
```

## 添加新工具

1. 在 `frontend/src/views/` 创建新工具页面组件
2. 在 `frontend/src/router/index.js` 添加路由
3. 在 `frontend/src/views/Dashboard.vue` 的 `tools` 数组中添加工具信息
4. 在 `backend/internal/handlers/tools.go` 添加后端处理逻辑
5. 在 `backend/cmd/main.go` 注册新的 API 路由

## 安全性

- 所有工具页面需要身份验证
- JWT Token 用于 API 认证
- 密码使用 bcrypt 哈希存储
- CORS 配置限制前端域名

## 后续开发

- 添加更多工具（JSON格式化、Base64编码、正则测试等）
- 用户权限管理
- 工具使用统计
- 界面主题切换
- 移动端适配

## 许可证

MIT