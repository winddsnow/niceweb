<script setup lang="ts">
import { ref, onMounted } from 'vue'

interface Shape {
  x: number
  y: number
  size: number
  rotation: number
  speed: number
  type: 'circle' | 'triangle' | 'square' | 'ring'
  opacity: number
  hue: number
}

const shapes = ref<Shape[]>([])

onMounted(() => {
  const types: Shape['type'][] = ['circle', 'triangle', 'square', 'ring']
  const arr: Shape[] = []
  for (let i = 0; i < 18; i++) {
    arr.push({
      x: Math.random() * 100,
      y: Math.random() * 100,
      size: Math.random() * 30 + 10,
      rotation: Math.random() * 360,
      speed: Math.random() * 20 + 10,
      type: types[Math.floor(Math.random() * types.length)],
      opacity: Math.random() * 0.12 + 0.03,
      hue: Math.random() * 60 + 230,
    })
  }
  shapes.value = arr
})
</script>

<template>
  <div class="floating-shapes">
    <div
      v-for="(s, i) in shapes"
      :key="i"
      class="shape"
      :class="s.type"
      :style="{
        left: s.x + '%',
        top: s.y + '%',
        width: s.size + 'px',
        height: s.size + 'px',
        opacity: s.opacity,
        animationDuration: s.speed + 's',
        animationDelay: -i * 1.5 + 's',
        '--hue': s.hue,
        transform: `rotate(${s.rotation}deg)`,
      }"
    />
  </div>
</template>

<style scoped>
.floating-shapes {
  position: fixed;
  inset: 0;
  z-index: 0;
  pointer-events: none;
  overflow: hidden;
}
.shape {
  position: absolute;
  animation: float-around linear infinite;
}
.shape.circle {
  border-radius: 50%;
  background: hsla(var(--hue), 70%, 70%, 0.3);
}
.shape.square {
  border-radius: 4px;
  background: hsla(var(--hue), 60%, 70%, 0.2);
}
.shape.triangle {
  width: 0 !important;
  height: 0 !important;
  background: transparent;
  border-left: 12px solid transparent;
  border-right: 12px solid transparent;
  border-bottom: 20px solid hsla(var(--hue), 70%, 70%, 0.2);
}
.shape.ring {
  border-radius: 50%;
  border: 2px solid hsla(var(--hue), 70%, 70%, 0.25);
  background: transparent;
}

@keyframes float-around {
  0% {
    transform: translateY(0) rotate(0deg);
  }
  25% {
    transform: translateY(-30px) rotate(90deg) translateX(20px);
  }
  50% {
    transform: translateY(-10px) rotate(180deg) translateX(-15px);
  }
  75% {
    transform: translateY(-40px) rotate(270deg) translateX(10px);
  }
  100% {
    transform: translateY(0) rotate(360deg);
  }
}
</style>
