<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <main>
    <RouterView />  
  </main>
</template>

<style scoped>
main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
