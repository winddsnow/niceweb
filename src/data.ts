export const skills = [
  { name: 'Vue 3', level: 95, color: '#42b883', icon: '💚' },
  { name: 'TypeScript', level: 90, color: '#3178c6', icon: '💙' },
  { name: 'React', level: 85, color: '#61dafb', icon: '⚛️' },
  { name: 'Node.js', level: 82, color: '#68a063', icon: '🟢' },
  { name: 'Three.js / WebGL', level: 75, color: '#ffffff', icon: '🎮' },
  { name: 'Tailwind CSS', level: 92, color: '#38bdf8', icon: '🎨' },
  { name: 'Vite / Build Tools', level: 88, color: '#bd34fe', icon: '⚡' },
  { name: 'Docker / DevOps', level: 70, color: '#2496ed', icon: '🐳' },
]

export const projects = [
  {
    title: '3D 数据可视化平台',
    desc: '基于 Three.js + WebGL 的实时 3D 数据渲染引擎，支持百万级数据点流畅展示',
    tags: ['Three.js', 'WebGL', 'D3.js', 'WebSocket'],
    gradient: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
    icon: '🌐',
  },
  {
    title: 'AI 智能编辑器',
    desc: '融合大语言模型的下一代代码编辑器，支持实时协作与智能补全',
    tags: ['Vue 3', 'Monaco Editor', 'OpenAI', 'CRDT'],
    gradient: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
    icon: '🤖',
  },
  {
    title: '微前端组件库',
    desc: '支持按需加载、主题定制的企业级 UI 组件系统，50+ 高质量组件',
    tags: ['Micro Frontend', 'Storybook', 'CSS-in-JS', 'A11y'],
    gradient: 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)',
    icon: '🧩',
  },
  {
    title: '实时协作白板',
    desc: '基于 CRDT 的多人实时协作白板应用，支持无限画布与矢量绘图',
    tags: ['Canvas', 'CRDT', 'WebSocket', 'Pinia'],
    gradient: 'linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)',
    icon: '✏️',
  },
  {
    title: '性能监控仪表盘',
    desc: '全链路前端性能监控系统，支持 LCP/FCP/CLS 等核心指标实时追踪',
    tags: ['Performance API', 'ECharts', 'Go', 'Prometheus'],
    gradient: 'linear-gradient(135deg, #fa709a 0%, #fee140 100%)',
    icon: '📊',
  },
  {
    title: '跨平台桌面应用',
    desc: '基于 Tauri 的轻量级跨平台桌面应用，包体小于 10MB',
    tags: ['Tauri', 'Rust', 'Vue 3', 'SQLite'],
    gradient: 'linear-gradient(135deg, #a18cd1 0%, #fbc2eb 100%)',
    icon: '🖥️',
  },
]

export const stats = [
  { label: '开源项目', value: 42, suffix: '+' },
  { label: 'GitHub Stars', value: 12800, suffix: '' },
  { label: '技术文章', value: 156, suffix: '篇' },
  { label: '贡献者', value: 380, suffix: '+' },
]

export const timeline = [
  {
    year: '2024',
    title: '全栈架构师',
    company: '某科技公司',
    desc: '主导微前端架构升级，支撑千万级用户',
  },
  {
    year: '2023',
    title: '高级前端工程师',
    company: '某互联网公司',
    desc: '负责核心产品前端架构与性能优化',
  },
  {
    year: '2022',
    title: '前端技术专家',
    company: '某创业公司',
    desc: '从零搭建前端团队与工程化体系',
  },
  {
    year: '2021',
    title: '前端开发工程师',
    company: '某软件公司',
    desc: '参与多个大型 B 端项目开发',
  },
]

export const navItems = [
  { label: '首页', href: '#hero' },
  { label: '关于', href: '#about' },
  { label: '技能', href: '#skills' },
  { label: '项目', href: '#projects' },
  { label: '经历', href: '#timeline' },
]
