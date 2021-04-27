<template>
  <div>
    <!-- Shop Section Begin -->
    <section class="shop spad disableselect" >
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="shop__sidebar">
                        <div class="shop__sidebar__search">
                            <div>
                                <input type="text" placeholder="Search..." v-model="searchCourseValue" @keyup.enter="searchCourse">
                                <button @click="searchCourse"><span class="icon_search"></span></button>
                            </div>
                        </div>
                        <div class="shop__sidebar__accordion">
                            <div class="accordion" id="accordionExample">
                                <div class="card">
                                    <div class="card-heading">
                                        <a data-toggle="collapse" data-target="#categories">Categories</a>
                                    </div>
                                    <div id="categories" class="collapse" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <div class="shop__sidebar__categories">
                                                <ul class="nice-scroll">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-heading">
                                        <a data-toggle="collapse" data-target="#filter">Filter</a>
                                    </div>
                                    <div id="filter" class="collapse" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <div class="shop__sidebar__price">
                                                <ul>
                                                    <li><a href="#">모두</a></li>
                                                    <li><a href="#">수강완료</a></li>
                                                    <li><a href="#">수강 시작 전</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="shop__product__option">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__left">
                                    <p>Showing 1–15 of {{CourseFilterList.length}} results</p>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__right">
                                  <div class="nice-select">
                                    <span class="current">{{currentSortValue}}</span>
                                    <ul class="list">
                                        <li class="option" value="review">리뷰순</li>
                                        <li value="rank">별점순</li>
                                        <li value="likeCnt">좋아요 순</li>
                                        <li value="arrival">최신순</li>
                                    </ul>
                                  </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                      <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(course,idx) in CourseFilterList" :key="idx">
                        <CourseItem :course="course" :idx="idx" />
                      </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="product__pagination">
                                <a class="active" href="#">1</a>
                                <a href="#">2</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Section End -->
  </div>
</template>


<script>
import CourseItem from '@/components/common/CourseItem.vue';
// import HomeItem from '@/components/HomeItem.vue';

export default {
  components: {
    CourseItem,
    // HomeItem
  },
  mounted() {
    this.setCategory();
    this.setCategoryCollapse();
    this.setSort();
  },
  data() {
    return {
      currentSortType: '',
      currentSortValue: '',
      searchCourseValue : '',
      categoryList : {},
      currentShowingList : [],
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
          date: '2021.04.17',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '323',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 4,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '양식',
          date: '2021.04.06',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '32000',
          likeCnt: '153',
          rate: '3.2',
        },
        {
          id: 5,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '양식',
          date: '2021.02.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '20000',
          likeCnt: '1223',
          rate: '3.1',
        },
        {
          id: 6,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '184',
          rate: '4.1',
        },
        {
          id: 7,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10200',
          likeCnt: '151',
          rate: '1.8',
        },
        {
          id: 8,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2020.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '12100',
          likeCnt: '531',
          rate: '4.6',
        },
        {
          id: 9,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 10,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.03.26',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '5823',
          rate: '3.9',
        },
        {
          id: 11,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.02.06',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 12,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 13,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 14,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 15,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 16,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 17,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 18,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
      ]
    }
  },
  methods : {
    searchCourse() {
      alert('Search Value',this.serachCourseValue);
    },
    setCategory() {
      this.CourseFilterList.forEach(element => {
        const key = element.category;
        const keys = Object.keys(this.categoryList);
        const isInclude = keys.includes(key);
        this.categoryList[key] = isInclude ? this.categoryList[key] + 1 : 1;
      });
      const categoryUl = document.querySelector('.nice-scroll');
      const keys = Object.keys(this.categoryList);
      keys.forEach(key => {
        const num = this.categoryList[key];
        const li = document.createElement('li');
        const a = document.createElement('a');
        a.innerText = `${key} (${num})`;
        li.appendChild(a);
        li.addEventListener('click',(e) =>{
          alert('클릭',e);
        })
        categoryUl.appendChild(li);
      });
    },
    setCategoryCollapse() {
      const collapesNodes = document.querySelectorAll('a[data-toggle]');
      collapesNodes.forEach(node => {
        const collapseTargetId = node.dataset.target;
        const collapseTarget = document.querySelector(collapseTargetId);
        node.addEventListener('click', () => {
          const flag = collapseTarget.classList.contains('show');
          const collapseHeight = collapseTarget.querySelectorAll('ul li').length
          collapseTarget.style.height = '0px';
          collapseTarget.setAttribute('class','collapsing');
          if (!flag) {
            setTimeout(() => {
              collapseTarget.style.height = `${collapseHeight * 35}px`;
            }, 0);
          }
          setTimeout(() => {
            flag ? collapseTarget.setAttribute('class','collapse') : collapseTarget.setAttribute('class','collapse show');
          }, 100);
        })
      }); 
    },
    setSort() {
      const sortList = document.querySelectorAll('.nice-select')[0];
      sortList.addEventListener('click', () => {
        sortList.setAttribute('class', 'nice-select open');
        this.currentSortType.classList.add('focus');
      });

      sortList.querySelectorAll('li').forEach((node, idx) => {
        if(idx === 0) {
          node.setAttribute('class', 'option focus selected');
          this.currentSortType = node;
          this.currentSortValue = node.innerText;
        } else {
          node.setAttribute('class', 'option');
        }
        node.addEventListener('click', (e) => {
					e.stopPropagation();

          this.currentSortType.classList.remove('selected');
          this.currentSortType.classList.remove('focus');
          sortList.setAttribute('class', 'nice-select');
          node.classList.add('selected');

          this.currentSortType = node;
          this.currentSortValue = node.innerText;
        })
      });
    }
  }
}
</script>

<style scoped>
.disableselect {
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none;   /* Chrome/Safari/Opera */
  -khtml-user-select: none;    /* Konqueror */
  -moz-user-select: none;      /* Firefox */
  -ms-user-select: none;       /* Internet Explorer/Edge*/
   user-select: none;          /* Non-prefixed version, currently 
                                  not supported by any browser */
}
.shop__sidebar__search div {
	position: relative;
}

.shop__sidebar__search div input {
	width: 100%;
	font-size: 15px;
	color: #b7b7b7;
	padding-left: 20px;
	border: 1px solid #e5e5e5;
	height: 42px;
}

.shop__sidebar__search div input::-webkit-input-placeholder {
	color: #b7b7b7;
}

.shop__sidebar__search div input::-moz-placeholder {
	color: #b7b7b7;
}

.shop__sidebar__search div input:-ms-input-placeholder {
	color: #b7b7b7;
}

.shop__sidebar__search div input::-ms-input-placeholder {
	color: #b7b7b7;
}

.shop__sidebar__search div input::placeholder {
	color: #b7b7b7;
}

.shop__sidebar__search div button {
	color: #b7b7b7;
	font-size: 15px;
	border: none;
	background: transparent;
	position: absolute;
	right: 0;
	padding: 0 15px;
	top: 0;
	height: 100%;
}
</style>
