<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

const progress = ref(0)

function onScroll() {
  const h = document.documentElement.scrollHeight - window.innerHeight
  progress.value = h > 0 ? (window.scrollY / h) * 100 : 0
}

onMounted(() => window.addEventListener('scroll', onScroll, { passive: true }))
onUnmounted(() => window.removeEventListener('scroll', onScroll))
</script>

<template>
  <div class="scroll-progress">
    <div class="scroll-bar" :style="{ width: progress + '%' }" />
    <div class="scroll-glow" :style="{ left: progress + '%' }" />
  </div>
</template>

<style scoped>
.scroll-progress {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  height: 3px;
  z-index: 1000;
  background: transparent;
}
.scroll-bar {
  height: 100%;
  background: var(--accent-gradient);
  border-radius: 0 2px 2px 0;
  transition: width 0.1s linear;
}
.scroll-glow {
  position: absolute;
  top: -4px;
  width: 60px;
  height: 12px;
  background: radial-gradient(ellipse, rgba(99,102,241,0.5), transparent);
  border-radius: 50%;
  transform: translateX(-50%);
  filter: blur(4px);
  transition: left 0.1s linear;
  pointer-events: none;
}
</style>
