<script setup>
import instance from '../modules/axiosModule.js'

export default {
  methods: {
    onMounted() {
      instance.post("/countUp", data)
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  }
}
</script>

<template>
</template>

<style scoped>
</style>
