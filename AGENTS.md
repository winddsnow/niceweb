# NiceWeb

Vue 3 + TypeScript + Vite portfolio homepage. Pure frontend, no backend, all mock data. Zero runtime deps beyond Vue 3.

## Commands

- `npm run dev` — Vite dev server on **port 3100** (non-standard)
- `npm run build` — runs `vue-tsc --noEmit && vite build` (type-checks before building)
- `npm run preview` — preview production build on **port 4173** (Vite default)
- `npm run lint` — ESLint + Prettier integration (`@eslint/js`, `typescript-eslint`, `eslint-plugin-vue`, `eslint-plugin-prettier`)
- `npm run format` — Prettier (config: `semi: false`, `singleQuote: true`, `trailingComma: all`, `printWidth: 100`, `tabWidth: 2`)
- No test framework is configured.

## Structure

Single package. Key files:

- `src/data.ts` — all mock content (skills, projects, stats, timeline, navItems). Edit this to change displayed data.
- `src/styles/global.css` — CSS custom properties for theming (`--accent-*`, `--text-*`, etc.). Edit vars here to change theme.
- `src/App.vue` — root component, imports all 12 section components, manages scroll listener.
- `src/main.ts` — entry point (4 lines).
- `src/components/` — 12 Vue components, one per page section.
- `index.html` — `lang="zh-CN"`.

## Conventions

- All components use `<script setup lang="ts">` (Composition API) + scoped `<style>`.
- Global styles via `@import './styles/global.css'` in `App.vue` `<style>` (unscoped).
- Theme toggle sets `data-theme="dark"` on `<html>`, CSS variables switch accordingly.
- Scroll animations use `IntersectionObserver` (see `fade-up`/`.visible` classes in `global.css`).

## Gotchas

- **`@/*` alias in tsconfig is NOT wired into Vite's `resolve.alias`** — `vite.config.ts` has no alias config. All imports use relative paths. Using `@/` imports will fail at runtime in dev/build.
- `noUnusedLocals` and `noUnusedParameters` are **true** in tsconfig — `vue-tsc --noEmit` will reject unused variables.
- Custom cursor is CSS-hidden on mobile (`@media (max-width: 768px)` — `cursor: auto`).
- Canvas particle systems resize on `window.resize` — ensure cleanup in `onUnmounted`.
- The `build` command fails on type errors (`vue-tsc --noEmit` runs first).
- No `.env` files exist — no environment variables are used.
