<template>
  <footer>
  <div class="inner">
    <div class="first_col"></div>
    <div class="logo">
      Real-Estate
    </div>
    <div class="text">
      Центр недвижимости "Актив-центр"
      Помогаем людям находить оптимальные решения с недвижимостью.
      Данный сайт носит исключительно информационный характер и не является публичной офертой.
    </div>
<!--    <div class="social">-->
<!--      Наши социальные сети:-->
<!--      <a href="#" target="_blank">-->
<!--        <i class="fab fa-facebook"></i>-->
<!--      </a>-->
<!--      <a href="#" target="_blank">-->
<!--        <i class="fab fa-twitter"></i>-->
<!--      </a>-->
<!--      <a href="#" target="_blank">-->
<!--        <i class="fab fa-instagram"></i>-->
<!--      </a>-->
<!--    </div>-->
<!--    <div class="second_col"></div>-->
  </div>
<!--    <form class="subscribe" action="/" method="post">-->
<!--      <input class="subscribe__input" type="phone" name="name" placeholder="Номер телефона">-->
<!--      <button class="subscribe__btn" type="submit">Обратный звонок</button>-->
<!--    </form>-->
  </footer>
</template>

<script>
import {Vue, Options} from "vue-class-component";

@Options({
  name: "intro-footer"
})
export default class IntroFooter extends Vue {
}
</script>

<style scoped>
.first_col {
  width: 50%;
}
.second_col {
  width: 10%;
}
.logo {
  margin: 30px 0;
  font-size: 30px;
  font-weight: bold;
  color: #333333;
}
.text{
  margin-bottom: 30px;
  font-size: 15px;
  color: #333333;
}
</style>