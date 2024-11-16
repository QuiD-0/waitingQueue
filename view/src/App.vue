<script setup>
import ItemBox from './components/ItemBox.vue'
</script>

<template>
  <main>
    <ItemBox url="https://www.naver.com"/>
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
