<template>
	<!-- Header Section Begin -->
	<header class="header">
			<div class="header__top">
					<div class="container">
							<div class="row">
									<div class="col-lg-6 col-md-7">
											<div class="header__top__left">
													<p>Free shipping, 30-day return or refund guarantee.</p>
											</div>
									</div>
									<div class="col-lg-6 col-md-5">
											<div class="header__top__right">
													<div class="header__top__links">
															<a href="#">Sign in</a>
															<a href="#">FAQs</a>
													</div>
													<div class="header__top__hover">
															<span>Usd <i class="arrow_carrot-down"></i></span>
															<ul>
																	<li>USD</li>
																	<li>EUR</li>
																	<li>USD</li>
															</ul>
													</div>
											</div>
									</div>
							</div>
					</div>
			</div>
			<div class="container">
					<div class="row">
							<div class="col-lg-3 col-md-3">
									<div class="header__logo">
											<a href="./index.html"><img src="@/assets/CREAMING-logo-white.png" alt=""></a>
									</div>
							</div>
							<div class="col-lg-6 col-md-6">
									<nav class="header__menu mobile-menu">
											<ul>
													<li class="active" @click="onClickNav('Home')">홈</li>
													<li @click="onClickNav('Course')">강의</li>
													<li @click="onClickNav('Creator')">강사</li>
													<li @click="onClickNav('Profile')">마이프로필</li>
											</ul>
									</nav>
							</div>
					</div>
					<div class="canvas__open"><i class="fa fa-bars"></i></div>
			</div>
	</header>
	<!-- Header Section End -->
</template>


<script>
export default {
	methods : {
		onClickNav(pathName) {
			this.$router.push({
				name: pathName
			})
		}
	}
}
</script>

<style>
</style>
