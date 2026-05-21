<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { projects } from '../data'

const sectionRef = ref<HTMLElement | null>(null)
const visible = ref(false)
const hoveredIdx = ref(-1)
const cardRefs = ref<HTMLElement[]>([])

onMounted(() => {
  const obs = new IntersectionObserver(
    ([e]) => { if (e.isIntersecting) visible.value = true },
    { threshold: 0.1 }
  )
  if (sectionRef.value) obs.observe(sectionRef.value)
})

function onMouseMove(e: MouseEvent, idx: number) {
  const card = cardRefs.value[idx]
  if (!card) return
  const rect = card.getBoundingClientRect()
  const x = (e.clientX - rect.left) / rect.width
  const y = (e.clientY - rect.top) / rect.height
  const rotateX = (0.5 - y) * 12
  const rotateY = (x - 0.5) * 12
  card.style.transform = `perspective(600px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) scale(1.02)`
}

function onMouseLeave(idx: number) {
  hoveredIdx.value = -1
  const card = cardRefs.value[idx]
  if (card) card.style.transform = ''
}
</script>

<template>
  <section id="projects" ref="sectionRef" class="projects">
    <div class="section-container">
      <div class="projects-header fade-up" :class="{ visible }">
        <h2 class="section-title">精选 <span class="gradient-text">项目</span></h2>
        <p class="section-subtitle">
          每个项目都是一次技术探索与实践的结晶。
        </p>
      </div>
      <div class="projects-grid">
        <div
          v-for="(p, i) in projects"
          :key="p.title"
          :ref="el => { if (el) cardRefs[i] = el as HTMLElement }"
          class="project-card glass-card fade-up"
          :class="{ visible, hovered: hoveredIdx === i }"
          :style="{ transitionDelay: `${0.08 + i * 0.08}s` }"
          @mouseenter="hoveredIdx = i"
          @mouseleave="onMouseLeave(i)"
          @mousemove="onMouseMove($event, i)"
        >
          <div class="project-bg" :style="{ background: p.gradient }" />
          <div class="project-content">
            <span class="project-icon">{{ p.icon }}</span>
            <h3 class="project-title">{{ p.title }}</h3>
            <p class="project-desc">{{ p.desc }}</p>
            <div class="project-tags">
              <span v-for="tag in p.tags" :key="tag" class="tag">{{ tag }}</span>
            </div>
          </div>
          <div class="project-shine" />
          <div class="project-border-glow" :style="{ background: p.gradient }" />
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.projects {
  padding: 120px 0;
  position: relative;
}
.projects-header {
  margin-bottom: 56px;
}
.projects-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(340px, 1fr));
  gap: 24px;
}
.project-card {
  position: relative;
  padding: 40px 32px 36px;
  overflow: hidden;
  min-height: 280px;
  display: flex;
  flex-direction: column;
  transition: transform 0.15s ease, box-shadow 0.4s ease;
  will-change: transform;
}
.project-bg {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 4px;
  opacity: 0;
  transition: opacity 0.4s ease;
}
.project-card.hovered .project-bg {
  opacity: 1;
}
.project-content {
  position: relative;
  z-index: 1;
  flex: 1;
}
.project-icon {
  font-size: 2.5rem;
  display: block;
  margin-bottom: 20px;
  transition: all 0.4s cubic-bezier(0.16, 1, 0.3, 1);
}
.project-card.hovered .project-icon {
  transform: scale(1.2) rotate(-8deg);
}
.project-title {
  font-size: 1.25rem;
  font-weight: 700;
  margin-bottom: 10px;
  letter-spacing: -0.01em;
}
.project-desc {
  font-size: 0.9rem;
  color: var(--text-secondary);
  line-height: 1.65;
  margin-bottom: 20px;
}
.project-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.tag {
  padding: 4px 12px;
  border-radius: 100px;
  font-size: 0.75rem;
  font-weight: 500;
  background: rgba(99, 102, 241, 0.08);
  color: var(--accent-1);
  border: 1px solid rgba(99, 102, 241, 0.12);
  transition: all 0.2s ease;
}
.project-card.hovered .tag {
  background: rgba(99, 102, 241, 0.12);
}
.project-shine {
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(255,255,255,0.08),
    transparent
  );
  transition: left 0.7s ease;
  pointer-events: none;
}
.project-card.hovered .project-shine {
  left: 100%;
}
.project-border-glow {
  position: absolute;
  inset: 0;
  border-radius: var(--radius-lg);
  padding: 1px;
  -webkit-mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);
  -webkit-mask-composite: xor;
  mask-composite: exclude;
  opacity: 0;
  transition: opacity 0.4s ease;
  pointer-events: none;
}
.project-card.hovered .project-border-glow {
  opacity: 0.5;
}
</style>
