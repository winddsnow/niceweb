# NiceWeb

Vue 3 + TypeScript + Vite portfolio homepage. Pure frontend, no backend, all mock data. Zero runtime deps beyond Vue 3.

## Commands

- `npm run dev` — Vite dev server on **port 3100** (non-standard)
- `npm run build` — runs `vue-tsc --noEmit && vite build` (type-checks before building)
- `npm run preview` — preview production build

There are no lint, format, or test commands. No ESLint, Prettier, or test framework is configured.

## Structure

Single package. Key files:

- `src/data.ts` — all mock content (skills, projects, stats, timeline, navItems). Edit this to change displayed data.
- `src/styles/global.css` — CSS custom properties for theming (`--accent-1`, `--accent-2`, etc.) and global styles.
- `src/App.vue` — root component, imports all 12 section components.
- `src/main.ts` — entry point (4 lines).
- `src/components/` — 12 Vue components, one per page section.
- `index.html` — `lang="zh-CN"`.

## Conventions

- All components use `<script setup lang="ts">` (Composition API).
- Scoped `<style>` blocks in every component (no CSS modules).
- Theme toggle sets `data-theme="dark"` on `<html>`, CSS variables switch accordingly.
- Scroll animations use `IntersectionObserver` (see `fade-up` class in `global.css`).
- Path alias `@/*` → `./src/*` (defined in `tsconfig.json`).
- `noUnusedLocals` and `noUnusedParameters` are **false** in tsconfig.

## Gotchas

- Custom cursor is CSS-hidden on mobile (`@media (max-width: 768px)` — `cursor: auto`).
- Canvas particle systems resize on `window.resize` — ensure cleanup in `onUnmounted`.
- The `build` command fails on type errors (`vue-tsc --noEmit` runs first).
- No `.env` files exist — no environment variables are used.
