<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { timeline } from '../data'

const sectionRef = ref<HTMLElement | null>(null)
const visible = ref(false)

onMounted(() => {
  const obs = new IntersectionObserver(
    ([e]) => { if (e.isIntersecting) visible.value = true },
    { threshold: 0.15 }
  )
  if (sectionRef.value) obs.observe(sectionRef.value)
})
</script>

<template>
  <section id="timeline" ref="sectionRef" class="timeline-section">
    <div class="section-container">
      <div class="timeline-header fade-up" :class="{ visible }">
        <h2 class="section-title">工作 <span class="gradient-text">经历</span></h2>
        <p class="section-subtitle">
          每一段经历都是成长路上的重要里程碑。
        </p>
      </div>
      <div class="timeline">
        <div class="timeline-line" />
        <div
          v-for="(item, i) in timeline"
          :key="item.year"
          class="timeline-item fade-up"
          :class="{ visible, left: i % 2 === 0, right: i % 2 !== 0 }"
          :style="{ transitionDelay: `${0.1 + i * 0.12}s` }"
        >
          <div class="timeline-dot" />
          <div class="timeline-card glass-card">
            <span class="timeline-year">{{ item.year }}</span>
            <h3 class="timeline-title">{{ item.title }}</h3>
            <span class="timeline-company">{{ item.company }}</span>
            <p class="timeline-desc">{{ item.desc }}</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.timeline-section {
  padding: 120px 0;
  position: relative;
}
.timeline-header {
  margin-bottom: 64px;
}
.timeline {
  position: relative;
  max-width: 800px;
  margin: 0 auto;
}
.timeline-line {
  position: absolute;
  left: 50%;
  top: 0;
  bottom: 0;
  width: 2px;
  background: linear-gradient(to bottom, var(--accent-1), var(--accent-2), transparent);
  transform: translateX(-50%);
}
.timeline-item {
  position: relative;
  display: flex;
  margin-bottom: 40px;
}
.timeline-item.left {
  justify-content: flex-start;
  padding-right: calc(50% + 30px);
}
.timeline-item.right {
  justify-content: flex-end;
  padding-left: calc(50% + 30px);
}
.timeline-dot {
  position: absolute;
  left: 50%;
  top: 24px;
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: var(--bg-primary);
  border: 3px solid var(--accent-1);
  transform: translateX(-50%);
  z-index: 2;
  transition: all 0.3s ease;
}
.timeline-item:hover .timeline-dot {
  box-shadow: 0 0 16px rgba(99,102,241,0.5);
  background: var(--accent-1);
  transform: translateX(-50%) scale(1.3);
}
.timeline-card {
  padding: 28px;
  width: 100%;
}
.timeline-year {
  font-family: 'JetBrains Mono', monospace;
  font-size: 0.85rem;
  font-weight: 700;
  color: var(--accent-1);
  margin-bottom: 8px;
  display: block;
}
.timeline-title {
  font-size: 1.1rem;
  font-weight: 700;
  margin-bottom: 4px;
}
.timeline-company {
  font-size: 0.85rem;
  color: var(--text-muted);
  margin-bottom: 10px;
  display: block;
}
.timeline-desc {
  font-size: 0.9rem;
  color: var(--text-secondary);
  line-height: 1.6;
}

@media (max-width: 768px) {
  .timeline-line { left: 20px; }
  .timeline-item.left,
  .timeline-item.right {
    padding-left: 50px;
    padding-right: 0;
    justify-content: flex-start;
  }
  .timeline-dot { left: 20px; }
}
</style>
