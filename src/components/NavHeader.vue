<script setup lang="ts">
import { ref, computed } from 'vue'
import { navItems } from '../data'

const props = defineProps<{ scrollY: number }>()

const isScrolled = computed(() => props.scrollY > 50)
const isDark = ref(false)

function toggleTheme() {
  isDark.value = !isDark.value
  document.documentElement.setAttribute('data-theme', isDark.value ? 'dark' : 'light')
}

function scrollTo(href: string) {
  const el = document.querySelector(href)
  el?.scrollIntoView({ behavior: 'smooth' })
}
</script>

<template>
  <header class="nav" :class="{ scrolled: isScrolled }">
    <div class="nav-inner section-container">
      <a class="nav-logo hoverable" href="#hero" @click.prevent="scrollTo('#hero')">
        <span class="logo-icon">✦</span>
        <span class="logo-text">NiceWeb</span>
      </a>
      <nav class="nav-links">
        <a
          v-for="item in navItems"
          :key="item.href"
          :href="item.href"
          class="nav-link hoverable"
          @click.prevent="scrollTo(item.href)"
        >
          {{ item.label }}
        </a>
        <button class="theme-toggle hoverable" @click="toggleTheme" :title="isDark ? '浅色模式' : '深色模式'">
          <span v-if="isDark" class="theme-icon">☀️</span>
          <span v-else class="theme-icon">🌙</span>
        </button>
      </nav>
    </div>
  </header>
</template>

<style scoped>
.nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  padding: 20px 0;
  transition: all 0.4s cubic-bezier(0.16, 1, 0.3, 1);
}
.nav.scrolled {
  padding: 12px 0;
  background: rgba(248, 249, 254, 0.85);
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  border-bottom: 1px solid var(--border-color);
  box-shadow: var(--shadow-sm);
}
[data-theme="dark"] .nav.scrolled {
  background: rgba(10, 10, 15, 0.85);
}
.nav-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.nav-logo {
  display: flex;
  align-items: center;
  gap: 10px;
  text-decoration: none;
  color: var(--text-primary);
  font-weight: 700;
  font-size: 1.2rem;
}
.logo-icon {
  font-size: 1.4rem;
  background: var(--accent-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  animation: pulse-glow 3s ease-in-out infinite;
}
@keyframes pulse-glow {
  0%, 100% { filter: drop-shadow(0 0 4px rgba(99,102,241,0.4)); }
  50% { filter: drop-shadow(0 0 12px rgba(99,102,241,0.8)); }
}
.logo-text {
  letter-spacing: -0.02em;
}
.nav-links {
  display: flex;
  align-items: center;
  gap: 8px;
}
.nav-link {
  text-decoration: none;
  color: var(--text-secondary);
  font-size: 0.9rem;
  font-weight: 500;
  padding: 8px 16px;
  border-radius: var(--radius-sm);
  transition: all 0.3s ease;
  position: relative;
}
.nav-link:hover {
  color: var(--text-primary);
  background: rgba(99, 102, 241, 0.06);
}
.nav-link::after {
  content: '';
  position: absolute;
  bottom: 4px;
  left: 50%;
  width: 0;
  height: 2px;
  background: var(--accent-gradient);
  border-radius: 1px;
  transition: all 0.3s ease;
  transform: translateX(-50%);
}
.nav-link:hover::after {
  width: 20px;
}
.theme-toggle {
  background: none;
  border: 1px solid var(--border-color);
  border-radius: var(--radius-sm);
  padding: 8px 12px;
  font-size: 1rem;
  transition: all 0.3s ease;
  margin-left: 8px;
}
.theme-toggle:hover {
  background: rgba(99, 102, 241, 0.06);
  border-color: var(--accent-1);
  transform: rotate(20deg);
}
.theme-icon {
  display: block;
  line-height: 1;
}

@media (max-width: 768px) {
  .nav-links { display: none; }
}
</style>
