<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const dotX = ref(0)
const dotY = ref(0)
const ringX = ref(0)
const ringY = ref(0)
const isHovering = ref(false)
const isPressed = ref(false)
const visible = ref(false)

let raf: number

function onMouseMove(e: MouseEvent) {
  dotX.value = e.clientX
  dotY.value = e.clientY
  if (!visible.value) {
    visible.value = true
    ringX.value = e.clientX
    ringY.value = e.clientY
  }
}

function animate() {
  ringX.value += (dotX.value - ringX.value) * 0.15
  ringY.value += (dotY.value - ringY.value) * 0.15
  raf = requestAnimationFrame(animate)
}

function onMouseOver(e: MouseEvent) {
  const t = e.target as HTMLElement
  if (t.closest('a, button, .hoverable')) isHovering.value = true
}

function onMouseDown() {
  isPressed.value = true
}
function onMouseUp() {
  isPressed.value = false
}

onMounted(() => {
  document.addEventListener('mousemove', onMouseMove)
  document.addEventListener('mouseover', onMouseOver)
  document.addEventListener('mousedown', onMouseDown)
  document.addEventListener('mouseup', onMouseUp)
  animate()
})

onUnmounted(() => {
  document.removeEventListener('mousemove', onMouseMove)
  document.removeEventListener('mouseover', onMouseOver)
  document.removeEventListener('mousedown', onMouseDown)
  document.removeEventListener('mouseup', onMouseUp)
  cancelAnimationFrame(raf)
})
</script>

<template>
  <div v-show="visible" class="cursor-wrapper">
    <div
      class="cursor-dot"
      :class="{ pressed: isPressed }"
      :style="{ transform: `translate(${dotX - 4}px, ${dotY - 4}px)` }"
    />
    <div
      class="cursor-ring"
      :class="{ hovering: isHovering, pressed: isPressed }"
      :style="{ transform: `translate(${ringX - 20}px, ${ringY - 20}px)` }"
    />
    <div
      class="cursor-trail"
      :style="{ transform: `translate(${ringX - 3}px, ${ringY - 60}px)` }"
    />
  </div>
</template>

<style scoped>
.cursor-wrapper {
  position: fixed;
  inset: 0;
  z-index: 99999;
  pointer-events: none;
}
.cursor-dot {
  position: fixed;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: var(--accent-1);
  mix-blend-mode: difference;
  transition:
    width 0.2s,
    height 0.2s,
    background 0.2s;
  will-change: transform;
}
.cursor-dot.pressed {
  width: 6px;
  height: 6px;
  background: var(--accent-pink);
}
.cursor-ring {
  position: fixed;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 1.5px solid rgba(99, 102, 241, 0.4);
  transition:
    width 0.3s cubic-bezier(0.16, 1, 0.3, 1),
    height 0.3s cubic-bezier(0.16, 1, 0.3, 1),
    border-color 0.3s,
    background 0.3s;
  will-change: transform;
}
.cursor-ring.hovering {
  width: 60px;
  height: 60px;
  border-color: rgba(236, 72, 153, 0.5);
  background: rgba(236, 72, 153, 0.05);
}
.cursor-ring.pressed {
  width: 32px;
  height: 32px;
  border-color: var(--accent-pink);
}
.cursor-trail {
  position: fixed;
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: radial-gradient(circle, rgba(99, 102, 241, 0.6), transparent);
  filter: blur(2px);
  will-change: transform;
  opacity: 0.5;
}

@media (max-width: 768px) {
  .cursor-wrapper {
    display: none !important;
  }
}
</style>
