<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { skills } from '../data'

const sectionRef = ref<HTMLElement | null>(null)
const visible = ref(false)

onMounted(() => {
  const obs = new IntersectionObserver(
    ([e]) => {
      if (e.isIntersecting) visible.value = true
    },
    { threshold: 0.15 },
  )
  if (sectionRef.value) obs.observe(sectionRef.value)
})
</script>

<template>
  <section id="skills" ref="sectionRef" class="skills">
    <div class="section-container">
      <div class="skills-header fade-up" :class="{ visible }">
        <h2 class="section-title">技术 <span class="gradient-text">栈</span></h2>
        <p class="section-subtitle">持续学习，不断精进。以下是主要的技术能力分布。</p>
      </div>
      <div class="skills-grid">
        <div
          v-for="(skill, i) in skills"
          :key="skill.name"
          class="skill-item fade-up"
          :class="{ visible }"
          :style="{ transitionDelay: `${0.05 + i * 0.06}s` }"
        >
          <div class="skill-top">
            <span class="skill-icon">{{ skill.icon }}</span>
            <span class="skill-name">{{ skill.name }}</span>
            <span class="skill-pct">{{ skill.level }}%</span>
          </div>
          <div class="skill-bar">
            <div
              class="skill-fill"
              :class="{ animate: visible }"
              :style="{
                width: visible ? skill.level + '%' : '0%',
                background: `linear-gradient(90deg, ${skill.color}99, ${skill.color})`,
                transitionDelay: `${0.3 + i * 0.08}s`,
              }"
            />
            <div class="skill-glow" :style="{ left: skill.level + '%', background: skill.color }" />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.skills {
  padding: 120px 0;
  position: relative;
}
.skills-header {
  margin-bottom: 56px;
}
.skills-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(340px, 1fr));
  gap: 24px;
}
.skill-item {
  padding: 20px 0;
}
.skill-top {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 12px;
}
.skill-icon {
  font-size: 1.2rem;
}
.skill-name {
  font-weight: 600;
  font-size: 0.95rem;
  flex: 1;
}
.skill-pct {
  font-family: 'JetBrains Mono', monospace;
  font-size: 0.85rem;
  color: var(--text-muted);
  font-weight: 500;
}
.skill-bar {
  position: relative;
  height: 8px;
  background: rgba(99, 102, 241, 0.08);
  border-radius: 4px;
  overflow: visible;
}
.skill-fill {
  height: 100%;
  border-radius: 4px;
  width: 0;
  transition: width 1.2s cubic-bezier(0.16, 1, 0.3, 1);
  position: relative;
  box-shadow: 0 0 12px rgba(99, 102, 241, 0.2);
}
.skill-glow {
  position: absolute;
  top: -4px;
  width: 16px;
  height: 16px;
  border-radius: 50%;
  opacity: 0;
  transform: translateX(-50%);
  filter: blur(8px);
  transition: opacity 0.6s ease;
  pointer-events: none;
}
.skill-fill.animate ~ .skill-glow {
  opacity: 0.5;
  transition:
    left 1.2s cubic-bezier(0.16, 1, 0.3, 1),
    opacity 0.6s ease 1.5s;
}
</style>
