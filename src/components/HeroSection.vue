<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const canvasRef = ref<HTMLCanvasElement | null>(null)
const titleVisible = ref(false)
const typingText = ref('')
const typingDone = ref(false)

const fullText = '用代码构建数字世界的艺术'
const roles = ['全栈工程师', 'Vue/React 开发者', '开源爱好者', '可视化探索者']
const currentRole = ref(roles[0])
const roleIdx = ref(0)
const charIdx = ref(0)
const isDeleting = ref(false)

function scrollTo(selector: string) {
  document.querySelector(selector)?.scrollIntoView({ behavior: 'smooth' })
}

let roleTimer: ReturnType<typeof setTimeout>
function typeRole() {
  const role = roles[roleIdx.value]
  if (!isDeleting.value) {
    charIdx.value++
    currentRole.value = role.slice(0, charIdx.value)
    if (charIdx.value === role.length) {
      setTimeout(() => { isDeleting.value = true; typeRole() }, 2000)
      return
    }
    roleTimer = setTimeout(typeRole, 100)
  } else {
    charIdx.value--
    currentRole.value = role.slice(0, charIdx.value)
    if (charIdx.value === 0) {
      isDeleting.value = false
      roleIdx.value = (roleIdx.value + 1) % roles.length
      roleTimer = setTimeout(typeRole, 400)
      return
    }
    roleTimer = setTimeout(typeRole, 50)
  }
}

onMounted(() => {
  titleVisible.value = true

  // Typing title
  let i = 0
  const typeInterval = setInterval(() => {
    typingText.value = fullText.slice(0, i + 1)
    i++
    if (i >= fullText.length) {
      clearInterval(typeInterval)
      typingDone.value = true
    }
  }, 60)

  // Role rotation
  typeRole()

  // Particle canvas
  const canvas = canvasRef.value
  if (!canvas) return
  const ctx = canvas.getContext('2d')!
  let w = canvas.width = window.innerWidth
  let h = canvas.height = window.innerHeight
  const particles: Array<{
    x: number; y: number; vx: number; vy: number
    size: number; opacity: number; hue: number
  }> = []
  const mouse = { x: w / 2, y: h / 2 }

  for (let i = 0; i < 100; i++) {
    particles.push({
      x: Math.random() * w,
      y: Math.random() * h,
      vx: (Math.random() - 0.5) * 0.6,
      vy: (Math.random() - 0.5) * 0.6,
      size: Math.random() * 3 + 1,
      opacity: Math.random() * 0.4 + 0.1,
      hue: Math.random() * 80 + 220,
    })
  }

  function onMouse(e: MouseEvent) {
    mouse.x = e.clientX
    mouse.y = e.clientY
  }
  window.addEventListener('mousemove', onMouse)

  let raf: number
  function draw() {
    ctx.clearRect(0, 0, w, h)

    for (const p of particles) {
      p.x += p.vx
      p.y += p.vy
      if (p.x < 0 || p.x > w) p.vx *= -1
      if (p.y < 0 || p.y > h) p.vy *= -1

      const dx = mouse.x - p.x
      const dy = mouse.y - p.y
      const dist = Math.sqrt(dx * dx + dy * dy)
      if (dist < 180) {
        p.x -= dx * 0.015
        p.y -= dy * 0.015
      }

      ctx.beginPath()
      ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2)
      const isLight = window.matchMedia('(prefers-color-scheme: light)').matches || !document.documentElement.hasAttribute('data-theme')
      ctx.fillStyle = isLight
        ? `hsla(${p.hue}, 60%, 50%, ${p.opacity * 0.7})`
        : `hsla(${p.hue}, 70%, 70%, ${p.opacity})`
      ctx.fill()
    }

    for (let i = 0; i < particles.length; i++) {
      for (let j = i + 1; j < particles.length; j++) {
        const dx = particles[i].x - particles[j].x
        const dy = particles[i].y - particles[j].y
        const dist = Math.sqrt(dx * dx + dy * dy)
        if (dist < 140) {
          ctx.beginPath()
          ctx.moveTo(particles[i].x, particles[i].y)
          ctx.lineTo(particles[j].x, particles[j].y)
          ctx.strokeStyle = `rgba(99, 102, 241, ${0.12 * (1 - dist / 140)})`
          ctx.lineWidth = 0.6
          ctx.stroke()
        }
      }
    }

    raf = requestAnimationFrame(draw)
  }
  draw()

  const onResize = () => {
    w = canvas.width = window.innerWidth
    h = canvas.height = window.innerHeight
  }
  window.addEventListener('resize', onResize)

  onUnmounted(() => {
    cancelAnimationFrame(raf)
    clearTimeout(roleTimer)
    window.removeEventListener('mousemove', onMouse)
    window.removeEventListener('resize', onResize)
  })
})
</script>

<template>
  <section id="hero" class="hero">
    <canvas ref="canvasRef" class="hero-canvas" />
    <div class="hero-content" :class="{ visible: titleVisible }">
      <div class="hero-badge">
        <span class="badge-dot" />
        <span>探索前端无限可能</span>
      </div>
      <h1 class="hero-title">
        <span class="title-line typing-text">
          {{ typingText }}<span class="cursor" :class="{ blink: typingDone }">|</span>
        </span>
      </h1>
      <p class="hero-role">
        <span class="role-prefix">▸</span>
        {{ currentRole }}<span class="role-cursor">_</span>
      </p>
      <p class="hero-desc">
        专注于 Vue / React / Node.js / 可视化 / 创新技术
      </p>
      <div class="hero-actions">
        <a href="#projects" class="btn btn-primary hoverable" @click.prevent="scrollTo('#projects')">
          <span>查看作品</span>
          <svg width="16" height="16" viewBox="0 0 16 16" fill="none"><path d="M3 8h10M9 4l4 4-4 4" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></svg>
        </a>
        <a href="#about" class="btn btn-ghost hoverable" @click.prevent="scrollTo('#about')">
          了解更多
        </a>
      </div>
      <div class="hero-stats">
        <div class="stat-item">
          <span class="stat-num">5+</span>
          <span class="stat-label">年经验</span>
        </div>
        <div class="stat-divider" />
        <div class="stat-item">
          <span class="stat-num">42+</span>
          <span class="stat-label">开源项目</span>
        </div>
        <div class="stat-divider" />
        <div class="stat-item">
          <span class="stat-num">12.8K</span>
          <span class="stat-label">GitHub Stars</span>
        </div>
      </div>
    </div>
    <div class="scroll-indicator">
      <div class="scroll-mouse">
        <div class="scroll-wheel" />
      </div>
      <span class="scroll-text">SCROLL</span>
    </div>
  </section>
</template>

<style scoped>
.hero {
  position: relative;
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}
.hero-canvas {
  position: absolute;
  inset: 0;
  z-index: 1;
}
.hero-content {
  position: relative;
  z-index: 2;
  text-align: center;
  padding: 0 24px;
  opacity: 0;
  transform: translateY(30px);
  transition: all 1s cubic-bezier(0.16, 1, 0.3, 1) 0.2s;
}
.hero-content.visible {
  opacity: 1;
  transform: translateY(0);
}
.hero-badge {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 8px 20px;
  border-radius: 100px;
  background: rgba(99, 102, 241, 0.08);
  border: 1px solid rgba(99, 102, 241, 0.15);
  font-size: 0.85rem;
  color: var(--accent-1);
  margin-bottom: 32px;
  font-weight: 500;
  backdrop-filter: blur(10px);
}
.badge-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #4ade80;
  box-shadow: 0 0 8px rgba(74, 222, 128, 0.5);
  animation: blink 2s ease-in-out infinite;
}
.hero-title {
  font-size: clamp(2.8rem, 8vw, 5.5rem);
  font-weight: 900;
  line-height: 1.1;
  letter-spacing: -0.04em;
  margin-bottom: 20px;
  min-height: 1.2em;
}
.typing-text {
  background: var(--accent-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  background-size: 200% auto;
  animation: gradient-shift 4s ease-in-out infinite;
}
.cursor {
  -webkit-text-fill-color: var(--accent-1);
  opacity: 0;
  font-weight: 300;
}
.cursor.blink {
  animation: cursor-blink 0.8s step-end infinite;
}
@keyframes cursor-blink {
  0%, 100% { opacity: 1; }
  50% { opacity: 0; }
}
.hero-role {
  font-size: 1.3rem;
  color: var(--text-secondary);
  margin-bottom: 16px;
  font-family: 'JetBrains Mono', 'Noto Sans SC', monospace;
  font-weight: 500;
  min-height: 1.6em;
}
.role-prefix {
  color: var(--accent-1);
  margin-right: 8px;
}
.role-cursor {
  animation: cursor-blink 0.8s step-end infinite;
  color: var(--accent-1);
}
.hero-desc {
  font-size: 1.05rem;
  color: var(--text-muted);
  line-height: 1.8;
  margin-bottom: 40px;
}
.hero-actions {
  display: flex;
  gap: 16px;
  justify-content: center;
  margin-bottom: 60px;
}
.btn {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 14px 32px;
  border-radius: var(--radius-md);
  font-size: 0.95rem;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.3s ease;
}
.btn-primary {
  background: var(--accent-gradient);
  color: #fff;
  box-shadow: 0 4px 20px rgba(99, 102, 241, 0.25);
  background-size: 200% auto;
}
.btn-primary:hover {
  box-shadow: 0 8px 30px rgba(99, 102, 241, 0.4);
  transform: translateY(-2px) scale(1.02);
  background-position: right center;
}
.btn-ghost {
  background: var(--glass-bg);
  color: var(--text-secondary);
  border: 1px solid var(--border-color);
  backdrop-filter: blur(10px);
}
.btn-ghost:hover {
  color: var(--text-primary);
  border-color: var(--accent-1);
  background: rgba(99, 102, 241, 0.05);
  transform: translateY(-2px);
}
.hero-stats {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 32px;
}
.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.stat-num {
  font-size: 1.6rem;
  font-weight: 800;
  font-family: 'JetBrains Mono', monospace;
  background: var(--accent-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}
.stat-label {
  font-size: 0.8rem;
  color: var(--text-muted);
  margin-top: 4px;
}
.stat-divider {
  width: 1px;
  height: 32px;
  background: var(--border-color);
}

.scroll-indicator {
  position: absolute;
  bottom: 40px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
}
.scroll-mouse {
  width: 24px;
  height: 38px;
  border-radius: 12px;
  border: 2px solid var(--text-muted);
  display: flex;
  justify-content: center;
  padding-top: 8px;
}
.scroll-wheel {
  width: 3px;
  height: 8px;
  border-radius: 2px;
  background: var(--accent-1);
  animation: scroll-anim 1.5s ease-in-out infinite;
}
@keyframes scroll-anim {
  0% { transform: translateY(0); opacity: 1; }
  100% { transform: translateY(12px); opacity: 0; }
}
.scroll-text {
  font-size: 0.65rem;
  letter-spacing: 0.2em;
  color: var(--text-muted);
  font-weight: 600;
}
</style>
