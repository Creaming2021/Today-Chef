<template>
  <div>
    <!-- Page Preloder -->
      <!-- <div id="preloder">
          <div class="loader"></div>
      </div> -->

      <!-- Offcanvas Menu Begin -->
      <div class="offcanvas-menu-overlay"></div>
      <div class="offcanvas-menu-wrapper">
          <div class="offcanvas__option">
              <div class="offcanvas__links">
                  <a href="#">Sign in</a>
                  <a href="#">FAQs</a>
              </div>
              <div class="offcanvas__top__hover">
                  <span>Usd <i class="arrow_carrot-down"></i></span>
                  <ul>
                      <li>USD</li>
                      <li>EUR</li>
                      <li>USD</li>
                  </ul>
              </div>
          </div>
          <div class="offcanvas__nav__option">
              <a href="#" class="search-switch"><img src="@/assets/img/icon/search.png" alt=""></a>
              <a href="#"><img src="@/assets/img/icon/heart.png" alt=""></a>
              <a href="#"><img src="@/assets/img/icon/cart.png" alt=""> <span>0</span></a>
              <div class="price">$0.00</div>
          </div>
          <div id="mobile-menu-wrap"></div>
          <div class="offcanvas__text">
              <p>Free shipping, 30-day return or refund guarantee.</p>
          </div>
      </div>
      <!-- Offcanvas Menu End -->

      <!-- Hero Section Begin -->
      <section class="hero">
          <div class="hero__slider owl-carousel">
              <div class="hero__items set-bg" data-setbg="@/assets/img/hero/hero-1.jpg">
                  <div class="container">
                      <div class="row">
                          <div class="col-xl-5 col-lg-7 col-md-8">
                              <div class="hero__text">
                                  <h6>Summer Collection</h6>
                                  <h2>Fall - Winter Collections 2030</h2>
                                  <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                  commitment to exceptional quality.</p>
                                  <a href="#" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                  <div class="hero__social">
                                      <a href="#"><i class="fa fa-facebook"></i></a>
                                      <a href="#"><i class="fa fa-twitter"></i></a>
                                      <a href="#"><i class="fa fa-pinterest"></i></a>
                                      <a href="#"><i class="fa fa-instagram"></i></a>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="hero__items set-bg" data-setbg="@/assets/img/hero/hero-2.jpg">
                  <div class="container">
                      <div class="row">
                          <div class="col-xl-5 col-lg-7 col-md-8">
                              <div class="hero__text">
                                  <h6>Summer Collection</h6>
                                  <h2>Fall - Winter Collections 2030</h2>
                                  <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                  commitment to exceptional quality.</p>
                                  <a href="#" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                  <div class="hero__social">
                                      <a href="#"><i class="fa fa-facebook"></i></a>
                                      <a href="#"><i class="fa fa-twitter"></i></a>
                                      <a href="#"><i class="fa fa-pinterest"></i></a>
                                      <a href="#"><i class="fa fa-instagram"></i></a>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </section>
      <!-- Hero Section End -->
      
      <!-- Product Section Begin -->
      <section class="product spad">
          <div class="container">
              <div class="row">
                  <div class="col-lg-12">
                      <div class="section-title">
                          <span>Coming Soon</span>
                          <h2>오픈 예정 강의</h2>
                      </div>
                  </div>
              </div>
              <div class="row product__filter">
                <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(item,idx) in CourseFilterList" :key="idx">
                  <HomeItem :item="item" :idx="idx" />
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(course,idx) in CourseFilterList" :key="idx">
                  <CourseItem :course="course" :idx="idx" />
                </div>
              </div>
          </div>
      </section>
      <!-- Product Section End -->

      <!-- Categories Section Begin -->
      <section class="categories spad">
          <div class="container">
              <div class="row">
                  <div class="col-lg-3">
                      <div class="categories__text">
                          <h2>Clothings Hot <br /> <span>Shoe Collection</span> <br /> Accessories</h2>
                      </div>
                  </div>
                  <div class="col-lg-4">
                      <div class="categories__hot__deal">
                          <img src="@/assets/img/product-sale.png" alt="">
                          <div class="hot__deal__sticker">
                              <span>Sale Of</span>
                              <h5>$29.99</h5>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-4 offset-lg-1">
                      <div class="categories__deal__countdown">
                          <span>Deal Of The Week</span>
                          <h2>Multi-pocket Chest Bag Black</h2>
                          <div class="categories__deal__countdown__timer" id="countdown">
                              <div class="cd-item">
                                  <span>3</span>
                                  <p>Days</p>
                              </div>
                              <div class="cd-item">
                                  <span>1</span>
                                  <p>Hours</p>
                              </div>
                              <div class="cd-item">
                                  <span>50</span>
                                  <p>Minutes</p>
                              </div>
                              <div class="cd-item">
                                  <span>18</span>
                                  <p>Seconds</p>
                              </div>
                          </div>
                          <a href="#" class="primary-btn">Shop now</a>
                      </div>
                  </div>
              </div>
          </div>
      </section>
      <!-- Categories Section End -->

      <!-- Instagram Section Begin -->
      <section class="instagram spad">
          <div class="container">
              <div class="row">
                  <div class="col-lg-8">
                      <div class="instagram__pic">
                          <div class="instagram__pic__item set-bg" data-setbg="@/assets/img/instagram/instagram-1.jpg"></div>
                          <div class="instagram__pic__item set-bg" data-setbg="@/assets/img/instagram/instagram-2.jpg"></div>
                          <div class="instagram__pic__item set-bg" data-setbg="@/assets/img/instagram/instagram-3.jpg"></div>
                          <div class="instagram__pic__item set-bg" data-setbg="@/assets/img/instagram/instagram-4.jpg"></div>
                          <div class="instagram__pic__item set-bg" data-setbg="@/assets/img/instagram/instagram-5.jpg"></div>
                          <div class="instagram__pic__item set-bg" data-setbg="@/assets/img/instagram/instagram-6.jpg"></div>
                      </div>
                  </div>
                  <div class="col-lg-4">
                      <div class="instagram__text">
                          <h2>Instagram</h2>
                          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                          labore et dolore magna aliqua.</p>
                          <h3>#Male_Fashion</h3>
                      </div>
                  </div>
              </div>
          </div>
      </section>
      <!-- Instagram Section End -->

      <!-- Latest Blog Section Begin -->
      <section class="latest spad">
          <div class="container">
              <div class="row">
                  <div class="col-lg-12">
                      <div class="section-title">
                          <span>Latest News</span>
                          <h2>Fashion New Trends</h2>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <div class="col-lg-4 col-md-6 col-sm-6">
                      <div class="blog__item">
                          <div class="blog__item__pic set-bg" data-setbg="@/assets/img/blog/blog-1.jpg"></div>
                          <div class="blog__item__text">
                              <span><img src="@/assets/img/icon/calendar.png" alt=""> 16 February 2020</span>
                              <h5>What Curling Irons Are The Best Ones</h5>
                              <a href="#">Read More</a>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-4 col-md-6 col-sm-6">
                      <div class="blog__item">
                          <div class="blog__item__pic set-bg" data-setbg="@/assets/img/blog/blog-2.jpg"></div>
                          <div class="blog__item__text">
                              <span><img src="@/assets/img/icon/calendar.png" alt=""> 21 February 2020</span>
                              <h5>Eternity Bands Do Last Forever</h5>
                              <a href="#">Read More</a>
                          </div>
                      </div>
                  </div>
                  <div class="col-lg-4 col-md-6 col-sm-6">
                      <div class="blog__item">
                          <div class="blog__item__pic set-bg" data-setbg="@/assets/img/blog/blog-3.jpg"></div>
                          <div class="blog__item__text">
                              <span><img src="@/assets/img/icon/calendar.png" alt=""> 28 February 2020</span>
                              <h5>The Health Benefits Of Sunglasses</h5>
                              <a href="#">Read More</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </section>
      <!-- Latest Blog Section End -->


      <!-- Search Begin -->
      <div class="search-model">
          <div class="h-100 d-flex align-items-center justify-content-center">
              <div class="search-close-switch">+</div>
              <form class="search-model-form">
                  <input type="text" id="search-input" placeholder="Search here.....">
              </form>
          </div>
      </div>
  </div>
</template>

<script>
import HomeItem from '@/components/HomeItem.vue';
import CourseItem from '@/components/CourseItem.vue';

export default {
  name: "Home",
  components: {
    HomeItem,
    CourseItem
  },
  data() {
    return {
      CourseFilterList: [
        {
          id: 1,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 2,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 4,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 5,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 6,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 7,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 8,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
      ]
    }
  }
};



</script>
