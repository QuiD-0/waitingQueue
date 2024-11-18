<template>
    <div class="waiting-screen">
        <h1>Waiting...</h1>
        <p>Please wait while we process your request.</p>
    </div>
</template>

<script>
export default {
    name: 'WaitingScreen'
}
</script>

<style scoped>
.waiting-screen {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center;
}

.waiting-screen h1 {
    font-size: 2em;
    margin-bottom: 0.5em;
}

.waiting-screen p {
    font-size: 1.2em;
}
</style>