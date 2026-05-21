<div align="center">

# ✦ NiceWeb

**现代前端技术展示 · 个人作品集主页**

一个基于 Vue 3 + TypeScript + Vite 构建的炫酷静态首页，展示现代前端技术的视觉表现力。

[English](./README.en.md) · 中文

</div>

---

## 项目简介

NiceWeb 是一个纯前端静态页面，无需后端服务，所有数据使用 Mock 数据。项目集中展示了多种现代前端视觉特效与交互技术，可作为个人主页、作品集展示或前端技术探索的参考。

### 主要特性

- 🌌 **极光背景** — Canvas 实时渲染的多色光团动画
- ✨ **粒子系统** — 100 个粒子 + 鼠标排斥力 + 粒子间连线
- 🔮 **浮动几何图形** — 随机漂浮的三角、圆、方块、环形
- 🪞 **毛玻璃效果** — Glassmorphism 导航栏与卡片
- 🎴 **3D 倾斜卡片** — 鼠标跟随的 `perspective + rotateX/Y` 效果
- ⌨️ **打字机效果** — Hero 区域标题逐字打出 + 职业轮播
- ✏️ **自定义光标** — 跟随鼠标、悬停放大、点击收缩
- 📊 **滚动进度条** — 顶部渐变进度条 + 光晕尾迹
- 🌓 **明暗主题切换** — 支持 Light / Dark 双主题
- 📱 **响应式设计** — 适配桌面与移动端
- 🎭 **滚动触发动画** — IntersectionObserver 驱动的渐入效果
- 🔢 **数字计数器** — 缓动函数动画，滚动到视口时触发

## 技术栈

| 技术 | 用途 |
|------|------|
| [Vue 3](https://vuejs.org/) | 响应式 UI 框架 |
| [TypeScript](https://www.typescriptlang.org/) | 类型安全 |
| [Vite](https://vitejs.dev/) | 构建工具与开发服务器 |
| Canvas API | 粒子系统与极光背景 |
| CSS Custom Properties | 主题系统与样式管理 |
| IntersectionObserver | 滚动触发动画 |

> **零运行时依赖** — 除 Vue 3 外不依赖任何第三方库。

## 快速开始

### 环境要求

- Node.js 18+
- npm / yarn / pnpm

### 安装与运行

```bash
# 克隆项目
git clone https://github.com/winddsnow/niceweb.git
cd niceweb

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

浏览器访问 `http://localhost:3100`。

### 构建生产版本

```bash
npm run build
```

构建产物输出到 `dist/` 目录。

### 预览生产版本

```bash
npm run preview
```

## 项目结构

```
niceweb/
├── public/
│   └── vite.svg                 # Favicon
├── src/
│   ├── components/
│   │   ├── AboutSection.vue     # 关于我 — 玻璃拟态卡片
│   │   ├── AuroraBackground.vue # 极光背景 — Canvas 渲染
│   │   ├── CustomCursor.vue     # 自定义光标 — 跟随特效
│   │   ├── FloatingShapes.vue   # 浮动几何 — 随机漂浮图形
│   │   ├── FooterSection.vue    # 页脚 — 博客链接 + 备案信息
│   │   ├── HeroSection.vue      # Hero — 粒子 + 打字机 + 统计
│   │   ├── NavHeader.vue        # 导航 — 毛玻璃 + 主题切换
│   │   ├── ProjectsSection.vue  # 项目展示 — 3D 倾斜卡片
│   │   ├── ScrollProgress.vue   # 滚动进度条
│   │   ├── SkillsSection.vue    # 技能 — 动画进度条
│   │   ├── StatsSection.vue     # 数据统计 — 数字滚动
│   │   └── TimelineSection.vue  # 时间轴 — 工作经历
│   ├── styles/
│   │   └── global.css           # 全局样式 + 主题变量
│   ├── data.ts                  # Mock 数据
│   ├── App.vue                  # 根组件
│   └── main.ts                  # 入口文件
├── index.html
├── package.json
├── tsconfig.json
├── vite.config.ts
├── LICENSE                      # MIT
└── README.md
```

## 自定义

### 修改内容

编辑 `src/data.ts` 即可自定义：

- **技能列表** — 修改 `skills` 数组
- **项目展示** — 修改 `projects` 数组
- **数据统计** — 修改 `stats` 数组
- **工作经历** — 修改 `timeline` 数组
- **导航菜单** — 修改 `navItems` 数组

### 修改主题色

编辑 `src/styles/global.css` 中的 CSS 变量：

```css
:root {
  --accent-1: #6366f1;       /* 主色调 */
  --accent-2: #8b5cf6;       /* 辅助色 */
  --accent-pink: #ec4899;    /* 强调色 */
  --accent-gradient: linear-gradient(135deg, #6366f1, #a78bfa, #f472b6);
}
```

## 浏览器支持

| 浏览器 | 版本 |
|--------|------|
| Chrome | 90+ |
| Firefox | 90+ |
| Safari | 14+ |
| Edge | 90+ |

## 博客

更多技术分享与项目实战：[www.blog.winddsnow.top](https://www.blog.winddsnow.top)

## 许可证

[MIT License](./LICENSE) © 2026 [winddsnow](https://www.blog.winddsnow.top)
