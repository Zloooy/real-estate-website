<template>
  <header class="header">
  <div class="container">
    <div class="header__inner">
      <div class="header__logo">Real-Estate</div>

      <nav class="nav">
        <a class="nav__link" href="#">Город</a>
        <a class="nav__link" href="#">Тарифы</a>
        <a class="nav__link" href="#">Блог</a>
        <a class="nav__link" href="#">Оставить заявку</a>
      </nav>
    </div>

  </div>
  </header>

  <div class="intro">
  <div class="container">
    <div class="intro__inner">
      <img src="assets/images/intro.jpg" alt="">
      <a class="btn" href="#">Новостройки</a>
      <a class="btn" href="#">Вторичка</a>
      <a class="btn" href="#">Аренда</a>
    </div>
  </div>
  </div>

  <footer class="footer">
  <div class="container">
    <div class="footer__inner">
      <div class="footer__col footer__col--first">
        <div class="footer__logo">Real-Estate</div>
        <div class="footer__text">Центр недвижимости "Актив-центр"
          Помогаем людям находить оптимальные решения с недвижимостью.
          Данный сайт носит исключительно информационный характер и не является публичной офертой.
        </div>
        <div class="footer__social">
          <div class="footer__social-content">
            Наши социальные сети:
            <a href="#" target="_blank">
              <i class="fab fa-facebook"></i>
            </a>
            <a href="#" target="_blank">
              <i class="fab fa-twitter"></i>
            </a>
            <a href="#" target="_blank">
              <i class="fab fa-instagram"></i>
            </a>
          </div>
        </div>

        <form class="subscribe" action="/" method="post">
          <input class="subscribe__input" type="phone" name="name" placeholder="Номер телефона">
          <button class="subscribe__btn" type="submit">Обратный звонок</button>
        </form>
      </div><!-- /footer__col-->
      <div class="footer__col footer__col--second"></div>
      <div class="footer__col .footer__col--third">
        <div class="footer__title">С нами можно связаться:</div>

      </div>
    </div>
  </div>
  </footer>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name:"intro-page"
})
export default class IntroPage extends Vue{

}

</script>

<style scoped>
  body {

    font-family: 'Open Sans', sans-serif;

    font-size: 15px;
    line-height: 1.6;
    color: #333333;
  }
  *,
  *:before,
  *:after{
    box-sizing: border-box;
  }

  h1, h2, h3, h4, h5, h6{
    margin: 0;
  }
  /*Container*/

  .container{
    width: 100%;
    max-width: 1000px;
    margin: 0px auto;
  }

  /*Intro */

  .intro {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    height: 100vh;

    background-size: cover;
  }


  .intro__inner{
    width: 100%;
    margin: 0 auto;

    text-align: center;
  }

  /*Header*/

  .header{
    width: 100%;
    padding-top: 30px;

    position: absolute;
    top:0;
    left: 0;
    right: 0;
    z-index: 1000;
  }

  .header__inner{

    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .header__logo{
    font-size: 30px;
    font-weight: 700;
    color: #333333;

  }

  /*NAv*/

  .nav{
    font-size: 15px;
    text-transform: uppercase;
  }

  .nav__link{
    display: inline-block;
    margin: 0 15px;
    color: black;
    text-decoration: none;

    transition: color 0.2s linear;
  }

  .nav__link:hover{
    color: lightsalmon;
  }

  /*button*/
  .btn{
    display: inline-block;
    vertical-align: top;
    padding: 10px 15px;

    margin: 60px auto 0;
    border: 3px solid black;

    font-size: 15px;
    font-weight: bold;
    color: black;

    text-transform: uppercase;
    transition: color 0.2s linear;
  }

  .btn:hover{
    color: lightsalmon;
  }

  /* Footer */
  .footer{
    padding-top: 60px;

  }

  .footer__inner{
    padding-bottom: 60px;
    display: flex;
  }

  .footer__col--first {
    width: 50%;
  }

  .footer__col--second {
    width: 10%;
  }

  .footer__col--third {
    width: 40%;
  }

  .footer__logo{
    margin: 30px 0;
    font-size: 30px;
    font-weight: bold;
    color: #333333;

  }

  .footer__text{
    margin-bottom: 30px;
    font-size: 15px;
    color: black;
  }

  .footer__social-header{
    font-size: 15px;
    font-weight: bold;
    color: #333333;

  }

  .footer__social-content a{
    display: inline-block;
    vertical-align: middle;
    margin-left: 10px;

    color: #7488ab;
    padding-top: 15px;
    font-size: 20px;
  }

  .footer__title{
    margin: 30px 0;

    font-size: 15px;
    font-weight: bold;
    color: black;
    text-transform: uppercase;
  }

  /* Subscribe */
  .subscribe {
    width: 100%;
    max-width: 380px;
    display: flex;
  }

  .subscribe__input{

    width: 50%;
    padding: 10px;
    height: 30px;

    border: 1px solid black;
    border-right: 0;

    font-family: 'Open Sans', sans-serif;
    font-size: 15px;
    line-height: 1.1;

    font-style: italic;
    font-weight: lighter;
  }

  .subscribe__input::placeholder{
    color: #cccccc;
  }

  .subscribe__input:focus {
    outline: 0;
    border-color: #95e1d3;
  }

  .subscribe__btn{
    width: 50%;
    height: 30px;

    background-color: #7488ab;
    border: 0;
    cursor: pointer;

    font-family: 'Open Sans', sans-serif;
    line-height: 1.1;
    font-size: 15px;
    font-weight: 700;
    text-transform: uppercase;

    transition: background .2s linear;
  }

  .subscribe__btn:hover{
    background-color: #61728d;
  }

  .subscribe__btn:focus {
    outline: 0;
  }

</style>