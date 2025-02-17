<template>
    <div class="waiting-screen">
        <h1>Waiting...</h1>
        <p>Please wait while we process your request.</p>
        <div>현재 내 대기순번 {{ count }}</div>
    </div>
</template>

<script>
import instance from '../modules/axiosModule.js'
export default {
    name: 'WaitingScreen',
    data() {
        return {
            redirectUrl: '',
            memberSeq: '',
            count: 0
        }
    },
    methods: {
        getParams() {
            const url = new URL(window.location.href)
            const params = new URLSearchParams(url.search)
            this.redirectUrl = params.get('redirectUrl')
            this.memberSeq = params.get('memberSeq')
        },
        getCount() {
            let queryParam = `?redirectUrl=${this.redirectUrl}&memberSeq=${this.memberSeq}`
            instance.get('/queue' + queryParam)
                .then(res => {
                    this.count = res.data.rank
                })
                .catch(err => {
                    console.log(err.response);
                })
        },
        connectSse() {
            let queryParam = `?memberSeq=${this.memberSeq}`
            let url = 'http://localhost:8080/sse'
            const eventSource = new EventSource(url + queryParam)
            eventSource.addEventListener("notify", (event) => {
                if (event.data === 'COMPLETE') {
                    eventSource.close()
                    window.location.href = this.redirectUrl
                }
            })
        }
    },
    mounted() {
        this.getParams()
        this.getCount()
        this.connectSse()
        setInterval(() => {
            if(this.count === 0) return
            this.getCount()
        }, 5_000)
    }
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
