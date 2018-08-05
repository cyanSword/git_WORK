<template>
	<div>
	<midCarousel/>
	<homeContent/>
    <Footer/>
	</div>
</template>

<script type="text/javascript">
	import masthed from '@/components/masthed'
	import midCarousel from '@/components/midCarousel'
	import homeContent from '@/components/homeContent'
	import Footer from '@/components/footer'
export default {
  components: {
    masthed,
    Footer,
   homeContent,
   midCarousel
  }
}
</script>