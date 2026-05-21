<script setup lang="ts">
</script>

<template>
  <footer class="footer">
    <div class="section-container">
      <div class="footer-cta fade-up visible">
        <div class="cta-card">
          <div class="cta-bg" />
          <div class="cta-content">
            <h3 class="cta-title">访问我的博客</h3>
            <p class="cta-desc">更多技术分享、项目实战与思考记录</p>
            <a href="https://www.blog.winddsnow.top" target="_blank" rel="noopener" class="cta-btn hoverable">
              <span>www.blog.winddsnow.top</span>
              <svg width="18" height="18" viewBox="0 0 16 16" fill="none">
                <path d="M4 12L12 4M12 4H6M12 4v6" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
              </svg>
            </a>
          </div>
        </div>
      </div>

      <div class="footer-top">
        <div class="footer-brand">
          <span class="footer-logo">✦ NiceWeb</span>
          <p class="footer-tagline">用代码构建数字世界的艺术</p>
        </div>
        <div class="footer-links-group">
          <div class="footer-col">
            <h4 class="footer-col-title">技术栈</h4>
            <a href="https://vuejs.org" target="_blank" class="footer-link">Vue.js</a>
            <a href="https://vitejs.dev" target="_blank" class="footer-link">Vite</a>
            <a href="https://typescriptlang.org" target="_blank" class="footer-link">TypeScript</a>
          </div>
          <div class="footer-col">
            <h4 class="footer-col-title">链接</h4>
            <a href="https://github.com" target="_blank" class="footer-link">GitHub</a>
            <a href="https://www.blog.winddsnow.top" target="_blank" class="footer-link highlight-link">博客 ↗</a>
          </div>
        </div>
      </div>

      <div class="footer-divider" />

      <div class="footer-bottom">
        <span class="footer-copy">
          © {{ new Date().getFullYear() }} NiceWeb · Built with Vue 3 + TypeScript + Vite
        </span>
        <a href="https://beian.miit.gov.cn/" target="_blank" rel="noopener" class="icp-link hoverable">
          粤ICP备2025511523号
        </a>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  padding: 0 0 40px;
  position: relative;
}

/* CTA Card */
.footer-cta {
  padding: 80px 0 60px;
}
.cta-card {
  position: relative;
  border-radius: var(--radius-xl);
  overflow: hidden;
  padding: 60px 48px;
  text-align: center;
}
.cta-bg {
  position: absolute;
  inset: 0;
  background: var(--accent-gradient);
  opacity: 0.08;
}
[data-theme="dark"] .cta-bg {
  opacity: 0.12;
}
.cta-content {
  position: relative;
  z-index: 1;
}
.cta-title {
  font-size: clamp(1.5rem, 3vw, 2rem);
  font-weight: 800;
  margin-bottom: 12px;
  letter-spacing: -0.02em;
}
.cta-desc {
  color: var(--text-secondary);
  font-size: 1rem;
  margin-bottom: 28px;
}
.cta-btn {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  padding: 16px 36px;
  border-radius: var(--radius-md);
  font-size: 1.05rem;
  font-weight: 700;
  text-decoration: none;
  background: var(--accent-gradient);
  color: #fff;
  box-shadow: 0 4px 24px rgba(99, 102, 241, 0.3);
  transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
  background-size: 200% auto;
  font-family: 'JetBrains Mono', monospace;
  letter-spacing: -0.01em;
}
.cta-btn:hover {
  box-shadow: 0 8px 40px rgba(99, 102, 241, 0.5);
  transform: translateY(-3px) scale(1.03);
  background-position: right center;
}

/* Footer body */
.footer-top {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding: 40px 0 32px;
}
.footer-logo {
  font-size: 1.2rem;
  font-weight: 700;
  background: var(--accent-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}
.footer-tagline {
  color: var(--text-muted);
  font-size: 0.85rem;
  margin-top: 8px;
}
.footer-links-group {
  display: flex;
  gap: 48px;
}
.footer-col {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.footer-col-title {
  font-size: 0.8rem;
  font-weight: 700;
  color: var(--text-muted);
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin-bottom: 4px;
}
.footer-link {
  color: var(--text-secondary);
  text-decoration: none;
  font-size: 0.9rem;
  transition: color 0.2s ease;
}
.footer-link:hover {
  color: var(--accent-1);
}
.highlight-link {
  color: var(--accent-1);
  font-weight: 600;
}
.highlight-link:hover {
  color: var(--accent-pink);
}

.footer-divider {
  height: 1px;
  background: var(--border-color);
  margin-bottom: 24px;
}

.footer-bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.footer-copy {
  font-size: 0.8rem;
  color: var(--text-muted);
}
.icp-link {
  font-size: 0.8rem;
  color: var(--text-muted);
  text-decoration: none;
  padding: 6px 14px;
  border-radius: var(--radius-sm);
  border: 1px solid var(--border-color);
  transition: all 0.3s ease;
  font-weight: 500;
}
.icp-link:hover {
  color: var(--accent-1);
  border-color: var(--accent-1);
  background: rgba(99, 102, 241, 0.05);
}

@media (max-width: 768px) {
  .footer-top { flex-direction: column; gap: 32px; }
  .footer-links-group { gap: 32px; }
  .footer-bottom { flex-direction: column; gap: 16px; text-align: center; }
  .cta-card { padding: 40px 24px; }
}
</style>
