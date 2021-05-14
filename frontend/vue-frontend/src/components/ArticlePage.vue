<template>
  <div class="article-page">
    <div class="header-article">
      Новостройки у метро Звездная
      <div class="article-data">
        14.05.2021
      </div>
    </div>

    <div class="img-article">
      <img src="https://cn-med.ru/media/1107/kvartira_u_metro_zvezdnaya.jpg?anchor=center&mode=crop&width=1920&rnd=132176877150000000">
    </div>
    <div class="text">
      <text-about
          about="В то время, как Приморский район признан самым популярным среди покупателей новостроек, желающих жить в Московском районе меньше не становится. Поэтому в южной части активно застраивается  территория мясоперерабатывающего завода «Самсон».

Общая площадь, принадлежавшая предприятию – порядка 54 гектаров. Он начал свою работу в 30-х годах прошлого века, а пик активности пришелся на 70-е. В 2000 году предприятие признали банкротом. В интернете ходят легенды, что закрытие завода спровоцировано намеренно. Но не будем углубляться.

После банкротства предприятия, главным собственником его земель стал Московский Индустриальный Банк. Освоение территорий своими силами он не потянул и начал продавать участки, которые конечно же оказались востребованными среди девелоперов.

Сначала стройка развернулась вдоль Пулковского шоссе и большинство домов там уже сдано. С точки зрения развития трансплртной сети и торговой инфраструктуры – это были лакомые кусочки. Автобусы и маршрутки ездят по Пулковскому шоссе до метро “Московская”, в аэропорт, в Петергоф, Пушкин, Павловск и другие исторические пригороды. Напротив жилого массива находятся торговые комплексы “Лето”, “OBI”, “Metro”, гипермаркет “Окей”. "
      />
    </div>
  </div>
</template>


<script lang="ts">
  import {Options, Vue} from "vue-class-component";
  import TextAbout from "@/components/TextAbout.vue";
  @Options({
    name: "article-page",
    props:{
      img:{
        type:String
      }
    },
    components:{
      TextAbout
    }
  })

  export default class ArticlePage extends Vue{

}

</script>

<style scoped>

.article-page{
  text-align: center;
  font-family: 'HelveticaNeueCyr', sans-serif;
  font-size: 15px;
  line-height: 1.6;
  color: #333333;
  max-width: 1060px;
  margin: 0 auto;
}

.header-article{
  text-align: center;
  margin-top: 60px;
  width: 100%;
  font-size: 30px;
  font-weight: 700;
}

.article-data{
  text-align: center;
  margin-top: 15px;
  color: steelblue;
  width: 100%;
  font-size: 15px;
}

.img-article{
  padding-top: 30px;
  height: auto;
  text-align: center;
  width: 100%;
}

.img-article>img{
  max-width: 100%;
  max-height: 390px;
}

.text{
  padding-top: 30px;
  max-width: 100%;
  text-align: left;
}

</style>