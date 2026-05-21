<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const canvasRef = ref<HTMLCanvasElement | null>(null)

onMounted(() => {
  const canvas = canvasRef.value
  if (!canvas) return
  const ctx = canvas.getContext('2d')!
  let w: number, h: number
  let t = 0

  function resize() {
    w = canvas!.width = window.innerWidth
    h = canvas!.height = window.innerHeight
  }
  resize()
  window.addEventListener('resize', resize)

  function draw() {
    t += 0.003
    ctx.clearRect(0, 0, w, h)

    const colors = [
      { r: 99, g: 102, b: 241 },
      { r: 167, g: 139, b: 250 },
      { r: 236, g: 72, b: 153 },
      { r: 6, g: 182, b: 212 },
    ]

    for (let i = 0; i < 4; i++) {
      const c = colors[i]
      const x = w * (0.2 + 0.6 * Math.sin(t + i * 1.5))
      const y = h * (0.3 + 0.4 * Math.cos(t * 0.7 + i * 2))
      const r = Math.min(w, h) * (0.15 + 0.05 * Math.sin(t * 0.5 + i))

      const grad = ctx.createRadialGradient(x, y, 0, x, y, r)
      grad.addColorStop(0, `rgba(${c.r},${c.g},${c.b},0.12)`)
      grad.addColorStop(0.5, `rgba(${c.r},${c.g},${c.b},0.04)`)
      grad.addColorStop(1, 'rgba(0,0,0,0)')
      ctx.fillStyle = grad
      ctx.fillRect(0, 0, w, h)
    }

    requestAnimationFrame(draw)
  }
  const raf = requestAnimationFrame(draw)

  onUnmounted(() => {
    cancelAnimationFrame(raf)
    window.removeEventListener('resize', resize)
  })
})
</script>

<template>
  <canvas ref="canvasRef" class="aurora-canvas" />
</template>

<style scoped>
.aurora-canvas {
  position: fixed;
  inset: 0;
  z-index: 0;
  pointer-events: none;
  opacity: 0.8;
}
</style>
