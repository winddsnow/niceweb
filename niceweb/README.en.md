<div align="center">

# ✦ NiceWeb

**Modern Frontend Tech Showcase · Portfolio Homepage**

A stunning static homepage built with Vue 3 + TypeScript + Vite, demonstrating the visual power of modern frontend technologies.

中文 · [English](./README.en.md)

</div>

---

## About

NiceWeb is a pure frontend static page with no backend dependencies — all data is mocked. The project showcases a collection of modern frontend visual effects and interaction techniques, serving as a reference for personal homepages, portfolio sites, or frontend tech exploration.

### Features

- 🌌 **Aurora Background** — Canvas-rendered multi-color light blobs with smooth animation
- ✨ **Particle System** — 100 particles with mouse repulsion and inter-particle connections
- 🔮 **Floating Shapes** — Randomly drifting triangles, circles, squares, and rings
- 🪞 **Glassmorphism** — Frosted glass navigation bar and cards
- 🎴 **3D Tilt Cards** — Mouse-tracking `perspective + rotateX/Y` effect
- ⌨️ **Typing Effect** — Hero title types out character by character with role rotation
- ✏️ **Custom Cursor** — Trailing dot + ring, hover expand, click shrink
- 📊 **Scroll Progress** — Gradient progress bar with glow trail at the top
- 🌓 **Theme Toggle** — Light / Dark mode switch
- 📱 **Responsive Design** — Adapts to desktop and mobile
- 🎭 **Scroll Animations** — IntersectionObserver-powered fade-in effects
- 🔢 **Animated Counters** — Eased number counting triggered on scroll

## Tech Stack

| Technology | Purpose |
|------------|---------|
| [Vue 3](https://vuejs.org/) | Reactive UI framework |
| [TypeScript](https://www.typescriptlang.org/) | Type safety |
| [Vite](https://vitejs.dev/) | Build tool & dev server |
| Canvas API | Particle system & aurora background |
| CSS Custom Properties | Theming & style management |
| IntersectionObserver | Scroll-triggered animations |

> **Zero runtime dependencies** — No third-party libraries beyond Vue 3.

## Getting Started

### Prerequisites

- Node.js 18+
- npm / yarn / pnpm

### Install & Run

```bash
# Clone the repo
git clone https://github.com/winddsnow/niceweb.git
cd niceweb

# Install dependencies
npm install

# Start dev server
npm run dev
```

Open `http://localhost:3100` in your browser.

### Production Build

```bash
npm run build
```

Output goes to the `dist/` directory.

### Preview Build

```bash
npm run preview
```

## Project Structure

```
niceweb/
├── public/
│   └── vite.svg                 # Favicon
├── src/
│   ├── components/
│   │   ├── AboutSection.vue     # About — glassmorphism cards
│   │   ├── AuroraBackground.vue # Aurora background — Canvas
│   │   ├── CustomCursor.vue     # Custom cursor — follow effect
│   │   ├── FloatingShapes.vue   # Floating shapes — drifting geometry
│   │   ├── FooterSection.vue    # Footer — blog link + ICP record
│   │   ├── HeroSection.vue      # Hero — particles + typing + stats
│   │   ├── NavHeader.vue        # Navbar — glassmorphism + theme toggle
│   │   ├── ProjectsSection.vue  # Projects — 3D tilt cards
│   │   ├── ScrollProgress.vue   # Scroll progress bar
│   │   ├── SkillsSection.vue    # Skills — animated progress bars
│   │   ├── StatsSection.vue     # Stats — animated counters
│   │   └── TimelineSection.vue  # Timeline — work experience
│   ├── styles/
│   │   └── global.css           # Global styles + theme variables
│   ├── data.ts                  # Mock data
│   ├── App.vue                  # Root component
│   └── main.ts                  # Entry point
├── index.html
├── package.json
├── tsconfig.json
├── vite.config.ts
├── LICENSE                      # MIT
└── README.md
```

## Customization

### Change Content

Edit `src/data.ts` to customize:

- **Skills** — `skills` array
- **Projects** — `projects` array
- **Stats** — `stats` array
- **Timeline** — `timeline` array
- **Navigation** — `navItems` array

### Change Theme Colors

Edit CSS variables in `src/styles/global.css`:

```css
:root {
  --accent-1: #6366f1;       /* Primary */
  --accent-2: #8b5cf6;       /* Secondary */
  --accent-pink: #ec4899;    /* Accent */
  --accent-gradient: linear-gradient(135deg, #6366f1, #a78bfa, #f472b6);
}
```

## Browser Support

| Browser | Version |
|---------|---------|
| Chrome  | 90+     |
| Firefox | 90+     |
| Safari  | 14+     |
| Edge    | 90+     |

## Blog

More technical articles and project posts: [www.blog.winddsnow.top](https://www.blog.winddsnow.top)

## License

[MIT License](./LICENSE) © 2026 [winddsnow](https://www.blog.winddsnow.top)
