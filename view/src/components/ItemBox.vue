<script>
import instance from '../modules/axiosModule.js'

export default {
  setup() {
    const callApi = (url) => {
      let data = {
        redirectUrl: url,
        memberSeq: 1
      }
      instance.post("/entry", data)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
    }
    return { callApi }
  },
  props: {
    url: {
      type: String,
      required: true
    }
  }
}
</script>

<template>
  <div class="item-box">
    <h2>상품</h2>
    <p>상품 설명</p>
    <p>가격: 1000원</p>
    <button @click="callApi(url)">구매 하기</button>
  </div>
</template>

<style scoped>
.item-box {
  padding: 1rem;
  border: 1px solid #333;
  border-radius: 0.5rem;
  background-color: #f9f9f9;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
