<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import CustomCursor from './components/CustomCursor.vue'
import ScrollProgress from './components/ScrollProgress.vue'
import AuroraBackground from './components/AuroraBackground.vue'
import FloatingShapes from './components/FloatingShapes.vue'
import NavHeader from './components/NavHeader.vue'
import HeroSection from './components/HeroSection.vue'
import AboutSection from './components/AboutSection.vue'
import SkillsSection from './components/SkillsSection.vue'
import ProjectsSection from './components/ProjectsSection.vue'
import TimelineSection from './components/TimelineSection.vue'
import StatsSection from './components/StatsSection.vue'
import FooterSection from './components/FooterSection.vue'

const scrollY = ref(0)

function onScroll() {
  scrollY.value = window.scrollY
}

onMounted(() => window.addEventListener('scroll', onScroll, { passive: true }))
onUnmounted(() => window.removeEventListener('scroll', onScroll))
</script>

<template>
  <div class="app">
    <CustomCursor />
    <ScrollProgress />
    <AuroraBackground />
    <FloatingShapes />
    <NavHeader :scroll-y="scrollY" />
    <main>
      <HeroSection />
      <AboutSection />
      <SkillsSection />
      <ProjectsSection />
      <TimelineSection />
      <StatsSection />
    </main>
    <FooterSection />
  </div>
</template>

<style>
@import './styles/global.css';
</style>
