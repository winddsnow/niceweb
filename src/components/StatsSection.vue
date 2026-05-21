<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { stats } from '../data'

const sectionRef = ref<HTMLElement | null>(null)
const visible = ref(false)
const counts = ref(stats.map(() => 0))

onMounted(() => {
  const obs = new IntersectionObserver(
    ([e]) => {
      if (e.isIntersecting) {
        visible.value = true
        animateCounters()
      }
    },
    { threshold: 0.3 }
  )
  if (sectionRef.value) obs.observe(sectionRef.value)
})

function animateCounters() {
  stats.forEach((s, i) => {
    const duration = 2000
    const start = performance.now()
    function step(now: number) {
      const elapsed = now - start
      const progress = Math.min(elapsed / duration, 1)
      const eased = 1 - Math.pow(1 - progress, 3)
      counts.value[i] = Math.round(eased * s.value)
      if (progress < 1) requestAnimationFrame(step)
    }
    requestAnimationFrame(step)
  })
}

function formatNum(n: number): string {
  if (n >= 10000) return (n / 1000).toFixed(1) + 'K'
  return n.toLocaleString()
}
</script>

<template>
  <section ref="sectionRef" class="stats">
    <div class="section-container">
      <div class="stats-grid fade-up" :class="{ visible }">
        <div
          v-for="(s, i) in stats"
          :key="s.label"
          class="stat-card"
        >
          <span class="stat-number">
            {{ formatNum(counts[i]) }}{{ s.suffix }}
          </span>
          <span class="stat-label-text">{{ s.label }}</span>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.stats {
  padding: 100px 0;
  position: relative;
}
.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
  padding: 60px 40px;
  background: var(--glass-bg);
  border: 1px solid var(--glass-border);
  border-radius: var(--radius-xl);
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  box-shadow: var(--shadow-md);
  position: relative;
  overflow: hidden;
}
.stats-grid::before {
  content: '';
  position: absolute;
  inset: 0;
  background: var(--accent-gradient);
  opacity: 0.03;
  pointer-events: none;
}
.stat-card {
  text-align: center;
  display: flex;
  flex-direction: column;
  gap: 8px;
  position: relative;
  z-index: 1;
}
.stat-number {
  font-size: clamp(2rem, 4vw, 3rem);
  font-weight: 900;
  font-family: 'JetBrains Mono', monospace;
  background: var(--accent-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  letter-spacing: -0.03em;
}
.stat-label-text {
  font-size: 0.9rem;
  color: var(--text-secondary);
  font-weight: 500;
}

@media (max-width: 768px) {
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
    padding: 40px 24px;
  }
}
</style>
