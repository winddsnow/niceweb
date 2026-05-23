<script setup lang="ts">
import { ref, onMounted } from 'vue'

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

const features = [
  { icon: '⚡', title: '极致性能', desc: '追求毫秒级响应，Lighthouse 评分持续 95+' },
  { icon: '🎨', title: '视觉设计', desc: '像素级还原设计稿，注重视觉层次与交互细节' },
  { icon: '🔧', title: '工程化', desc: '完善的 CI/CD、代码规范、自动化测试体系' },
  { icon: '🌐', title: '全栈能力', desc: '前后端贯通，独立完成产品从 0 到 1 的交付' },
]
</script>

<template>
  <section id="about" ref="sectionRef" class="about">
    <div class="section-container">
      <div class="about-header fade-up" :class="{ visible }">
        <h2 class="section-title">关于 <span class="gradient-text">我</span></h2>
        <p class="section-subtitle">
          热爱技术，享受用代码创造美好事物的过程。相信前端不仅是页面，
          更是连接用户与数字世界的桥梁。
        </p>
      </div>
      <div class="features-grid">
        <div
          v-for="(f, i) in features"
          :key="f.title"
          class="feature-card glass-card fade-up"
          :class="{ visible }"
          :style="{ transitionDelay: `${0.1 + i * 0.1}s` }"
        >
          <span class="feature-icon">{{ f.icon }}</span>
          <h3 class="feature-title">{{ f.title }}</h3>
          <p class="feature-desc">{{ f.desc }}</p>
          <div class="feature-glow" />
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.about {
  padding: 140px 0;
  position: relative;
}
.about-header {
  margin-bottom: 64px;
}
.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}
.feature-card {
  position: relative;
  padding: 36px 28px;
  overflow: hidden;
}
.feature-icon {
  font-size: 2.2rem;
  display: block;
  margin-bottom: 20px;
  transition: transform 0.3s ease;
}
.feature-card:hover .feature-icon {
  transform: scale(1.15) rotate(-5deg);
}
.feature-title {
  font-size: 1.15rem;
  font-weight: 700;
  margin-bottom: 10px;
  letter-spacing: -0.01em;
}
.feature-desc {
  font-size: 0.92rem;
  color: var(--text-secondary);
  line-height: 1.6;
}
.feature-glow {
  position: absolute;
  top: -50%;
  right: -50%;
  width: 200px;
  height: 200px;
  background: radial-gradient(circle, rgba(99, 102, 241, 0.1), transparent 70%);
  border-radius: 50%;
  transition: all 0.6s ease;
  pointer-events: none;
}
.feature-card:hover .feature-glow {
  transform: translate(-30px, 30px);
  opacity: 1.5;
}
</style>
