<script>
import instance from '../modules/axiosModule.js'

export default {
    data() {
        return {
            seq: 10
        }
    },
    methods: {
        addWaiting() {
            for (let i = 0; i < 10; i++) {
                this.postEntry(this.seq)
                this.seq++
            }
        },
        postEntry(seq) {
            let data = {
                memberSeq: seq,
                redirectUrl: 'https://www.naver.com'
            }
            instance.post("/entry", data)
                .catch(err => {
                    alert(err.response.data.message)
                })
        }
    }
}
</script>

<template>
    <div>
        <button @click="addWaiting">대기열 실행 버튼</button>
    </div>
</template>

<style scoped>
</style>