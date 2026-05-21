# AGENTS.md — NiceWeb

Vue 3 + TypeScript + Vite 静态首页项目。

## 项目结构

```
src/
├── components/          # 12 个 Vue 组件
│   ├── HeroSection      # 粒子系统 + 打字机效果
│   ├── AuroraBackground # Canvas 极光背景
│   ├── CustomCursor     # 自定义光标跟随
│   ├── FloatingShapes   # 浮动几何图形
│   ├── NavHeader        # 毛玻璃导航 + 主题切换
│   ├── AboutSection     # 玻璃拟态卡片
│   ├── SkillsSection    # 动画进度条
│   ├── ProjectsSection  # 3D 倾斜卡片
│   ├── TimelineSection  # 时间轴
│   ├── StatsSection     # 数字滚动计数器
│   ├── ScrollProgress   # 顶部滚动进度条
│   └── FooterSection    # 页脚 + 博客链接 + 备案
├── styles/global.css    # CSS 变量主题系统
├── data.ts              # Mock 数据（技能/项目/统计/经历）
├── App.vue              # 根组件
└── main.ts              # 入口
```

## 命令

```bash
npm install
npm run dev        # http://localhost:3100
npm run build      # vue-tsc --noEmit && vite build
npm run preview    # 预览构建产物
```

## 关键事实

- **零运行时依赖** — 除 Vue 3 外无第三方库
- **无测试、无 lint** — 纯展示项目
- **双主题** — Light（默认）/ Dark，通过导航栏按钮切换
- **所有数据 mock** — 编辑 `src/data.ts` 修改内容
- **主题色** — 修改 `src/styles/global.css` 中的 CSS 变量
- **ICP 备案** — 粤ICP备2025511523号（FooterSection.vue）
- **博客** — https://www.blog.winddsnow.top
