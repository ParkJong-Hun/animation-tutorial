package com.example.animation_controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/*애니메이션
원하는 애니메이션 유형에 따라 다양한 애니메이션 API가 있음.
 */
    /*비트맵 애니메이션
    아이콘, 이미지 같은 비트맵 그래픽을 애니메이션으로 만들려면 "드로어블 애니메이션 API"를 사용해야 함.
    일반적으로 드로어블 리소스를 사용해 정적으로 정의되지만, 개발자가 런타임 시 애니메이션 동작을 정의할 수 있음.
    */
        /*
        -드로어블 그래픽 애니메이션화
        이미지를 화면에 애니메이션으로 표시.
            1. 애니메이션 드로어블 사용.
            드로어블 리소스를 차례로 로드해 애니메이션을 만드는 것. 한 통의 필름처럼 여러 다른 이미지를 순서대로 재생해
            만드는 기본 애니메이션. 애니메이션의 기초.
            코드에서 애니메이션의 프레임을 정의할 수 있지만 AnimationDrawable 클래스 API를 사용하면 애니메이션을 구성하는
            프레임을 나열하는 단일 XML 파일로 더 간단하게 정의할 수 있음. 애니메이션 유형 XML 파일은 Android 프로젝트의
            res/drawable/ 디렉터리에 있음. 이 경우 명령어는 각 애니메이션 프레임의 순서와 지속 기간.
            XML 파일은 루트 노드인 <animation-list> 요소와 각각 프레임을 정의하는 일련의 하위 <item> 노드(프레임의 드로어블
            리소스 및 프레임 지속 기간)로 구성 됨.
                -예시

                [XML]
                <animation-list xmlns:android="http://schemas.android.com/apk/res/android"
                    android:oneshot="true">
                    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
                    <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
                    <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
                </animation-list>

                [Kotlin]
                private lateinit var rocketAnimation: AnimationDrawable

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.main)

                    val rocketImage = findViewById<ImageView>(R.id.rocket_image).apply {
                        setBackgroundResource(R.drawable.rocket_thrust)
                        rocketAnimation = background as AnimationDrawable
                    }

                    rocketImage.setOnClickListener({ rocketAnimation.start() })
                }
            2. 벡터 드로어블의 속성을 애니메이션화할 수 있는 애니메이션화된 벡터 드로어블을 사용함.
            벡터 드로어블은 픽셀화하거나 흐릿해지지 않고 확장 가능한 드로어블 유형. AnimatedVectorDrawable 클래스를 사용하면
            벡터 드로어블은 회전하거나 경로 데이터를 변경하여 다른 이미지로 변형하는 등으로 벡터 드로어블 속성을 애니메이션화할 수 있음.
            일반적으로 세가지 XML 파일로 정의.
                -res/drawable/에 <vector> 요소가 있는 벡터 드로어블
                -res/drawable/에 <animated-vector> 요소가 있는 애니메이션화 된 벡터 드로어블
                -res/animator/에 <objectAnimator> 요소가 있는 하나 이상의 객체 애니메이터
            애니메이션화된 벡터 드로어블을 통해 <group> 및 <path> 요소의 속성을 애니메이션화할 수 있음. <group> 요소를 통해
            경로 또는 하위 그룹 조합을 정의하고 <path> 요소를 통해 그릴 경로를 정의.
            애니메이터 정의에서 참조할 수 있도록 애니메이션화힐 수 있도록 애니메이션화할 벡터 드로어블을 정의할 때 android:name 속성을 사용해
            그룹 및 경로에 고유한 이름을 지정함.
                -예시

                [XML]
                res/drawable/vectordrawable.xml
                <vector xmlns:android="http://schemas.android.com/apk/res/android"
                    android:height="64dp"
                    android:width="64dp"
                    android:viewportHeight="600"
                    android:viewportWidth="600">
                    <group
                        android:name="rotationGroup"
                        android:pivotX="300.0"
                        android:pivotY="300.0"
                        android:rotation="45.0" >
                        <path
                            android:name="v"
                            android:fillColor="#000000"
                            android:pathData="M300,70 l 0,-70 70,70 0,0 -70,70z" />
                    </group>
                </vector>

                res/drawable/animatorvectordrawable.xml
                <animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
                  android:drawable="@drawable/vectordrawable" >
                    <target
                        android:name="rotationGroup"
                        android:animation="@animator/rotation" />
                    <target
                        android:name="v"
                        android:animation="@animator/path_morph" />
                </animated-vector>

                res/animator/rotation.xml
                <objectAnimator
                    android:duration="6000"
                    android:propertyName="rotation"
                    android:valueFrom="0"
                    android:valueTo="360" />

                res/animator/path_morph.xml
                <set xmlns:android="http://schemas.android.com/apk/res/android">
                    <objectAnimator
                        android:duration="3000"
                        android:propertyName="pathData"
                        android:valueFrom="M300,70 l 0,-70 70,70 0,0   -70,70z"
                        android:valueTo="M300,70 l 0,-70 70,0  0,140 -70,0 z"
                        android:valueType="pathType" />
                </set>
        */
    /*UI 가시성 및 모션 애니메이션
    레이아웃에서 뷰의 가시성 또는 위치를 변경해야 하는 경우 섬세한 애니메이션 포함.
    현재 레이아웃 내에서 뷰를 옮기거나 표시하거나 숨기려면 Android3.0(API 11)이상에서 사용할 수 있는 "android.animation 패키지의
    속성 애니메이션 시스템"을 사용하면 됨. 일정 기간 동안 View 객체의 속성을 업데이트해 속성이 변경되면 뷰를 계속 다시 그림.
    최소한의 노력우로 이런 애니메이션을 만들려면 레이아웃에서 애니메이션을 사용 설정.
    */
        /*
        -레이아웃 업데이트 자동 애니메이션 처리
        개발자가 레이아웃들 변경할 때마다 시스템에서 실행되는 미리 로드된 애니메이션 제공. 개발자는 레이아웃에 속성을 설정해 이러한 레이아웃 변경을
        애니메이션 처리하도록 시스템에 알려주기만 하면 됨.

            -레이아웃 만들기
            액티비티의 레이아웃 XML 파일에서 애니메이션을 사용 설정할 레이아웃의 android:animatedLayoutChanges 속성을 true로 설정.
                -예시
                <LinearLayout android:id="@+id/container"
                    android:animateLayoutChanges="true"
                    ...
                />

            -레이아웃에서 항목 추가, 업데이트 또는 삭제
            이제 레이아웃에서 항목을 추가, 삭제 또는 업데이트하기만 하면 항목이 자동으로 애니메이션됨.
                -예시
                lateinit var containerView: ViewGroup
                ...
                private fun addItem() {
                    val newView: View = ...

                    containerView.addView(newView, 0)
                }

         */
        /*
        -속성 애니메이션
        거의 모든 항목을 애니메이션으로 만들 수 있는 강력항 프레임워크. 애니메이션을 정의하면 화면에 그리는지에 관계없이 시간 경과에 따라 객체 속성을 변경할 수 있음.
        속성 애니메이션을 통해 지정된 시간 동안 속성 값을 변경. 특정 항목을 애니메이션으로 보여주려면 애니메이션화할 객체 속성 지정.
            -재생 시간: 애니메이션 재생 시간을 지정할 수 있음. 기본 길이는 300ms
            -시간 보간: 속성의 값이 애니메이션의 현재 경과 시간 함수로 계산되는 방식을 지정할 수 있음.
            -반복 횟수 및 동작: 재생 시간이 끝나고 애니메이션 반복 횟수에 도달하면 애니메이션 반복 여부를 지정하 수 있음. 역방향 재생도 지정 가능.
            -애니메이터 조합: 애니메이션을 함께 또는 순차적으로 재생하거나 지정된 지연 시간 후에 재생하는 논리 조합으로 그룹화할 수 있음.
            -프레임 새로고침 지연: 애니메이션 프레임의 새로 고침 빈도를 지정 가능. 기본값은 10ms마다 새로고치도록 설정하지만, 애플리케이션에서 프레임을 새로고침할 수
            있는 속도는 궁극적으로 시스템의 전반적인 시스템에서 기본 타이머를 제공하는 속도에 따라 달라짐.

            -작동 방식
            ValueAnimator 객체를 통해 애니메이션 실행 시간 및 애니메이션으로 표시되는 속성의 현재 값과 같은 애니메이션의 타이밍 추적.
            애니메이션의 보간을 정의하는 TimeInterpolator와 애니메이션으로 보여주는 속성 값의 계산 방법을 정의하는 TypeEvaluator를 캡슐화함.
            애니메이션을 시작하려면 ValueAnimator를 만들고 애니메이션으로 보여줄 속성의 시작, 끝 값고 함께 애니메이션 재생 시간을 지정.
            start()를 호출해 애니메이션을 시작. 전체 애니메이션 중 ValueAnimator에서는 애니메이션 재생 시간과 경과 시간에 따라 0과 1 사이의 경과된 비율 계산.
            경과된 비율은 애니메이션이 완료된 시간의 비율을 나타내며 0은 0%를 의미, 1은 100%를 의미.
            경과된 비율을 계산하면 현재 설정된 TimeInterpolator를 호출해 보간된 비율을 계산. 보간된 비율을 통해 설정된 시간 보간을 고려해 새 비율에 경과된 비율 매핑.
            보간된 비율을 계산할 때, ValueAnimator에서는 적절한 TtypeEvaluator를 호출해 보간된 비율, 애니메이션의 시작, 종교 값에 따라 애니메이션으로 보여줄 속성 값 계산.
            1. 선형 애니메이션
            객체가 일정한 속도로 움작임.
            2. 비선형 애니메이션
            가속, 감속 등이 가능. 일정한 속도로 움직이지 않음.

            -속성 애니메이션과 보기 애니메이션의 차이점
            보기 애니메이션 시스템에서는 View 객체만 애니메이션으로 보여주는 기능을 제공하므로, 객체를 애니메이션으로 보여주려면 자체 코드를 구현해야 함.
            보기 애니메이션 시스템은 보여중 몇 가지 요소만 노출한다는 점에서 제한되어 있음.
            또 다른 단점으로는 실제 보기 자체가 아니라 보기를 그린 위치만 수정한다는 것.
            속성 애니메이션 시스템에서는 이러한 제약 조건이 완전히 삭제되므로 모든 객체의 속성을 애니메이션으로 보여줄 수 있고 객체 자체가 실제로 수정됨.
            애니메이션을 수행하는 방식에서도 더 강력. 상위 수준에서 애니메이션으로 보여줄 색상, 위치 또는 크기 등의 속성에 애니메이터를 할당하고 여러 애니메이터의 보간 및
            동기화와 같은 애니메이션 요소를 정의할 수 있음.
            그러나 보기 애니메이션 시스템은 설정하는 데 시간이 덜 소요되고 작성해야 할 코드가 적음.
            보기 애니메이션에서 필요한 모든 작업을 수행하거나 기존 코드가 이미 원하는 대로 작동하는 경우 속성 애니메이션 시스템을 사용할 필요가 없음. 사용 사례가 생기면
            상황에 따라 두 애니메이션 시스템을 모두 사용할 수 있음.
        */
        /*
        -애니메이션으로 뷰 표시 또는 숨기기
        앱 사용 시 오래돈 정보가 삭제되는 동안 새 정보가 화면에 표시되어야 함. 표시 내용이 빠르게 전환되면 부자연스럽게 보이거나 사용자가 화면에서 새 콘텐츠를 쉽게 놓칠 수 있다.
        애니메이션을 사용하면 변화 속도가 느리고 움직임으로 사용자의 시선을 끌 수 있으므로 변경 사항을 더 분명하게 할 수 있음.
        뷰를 표시하거나 숨길 때 흔히 사용하는 애니메이션에는 3가지가 있음.
            1. 회전 표시 애니메이션
            표시 애니메이션은 UI 요소 그룹을 표시하거나 숨길 때 사용자에게 시각적 연속성을 제공.
            ViewAnimationUtils.createCircularReveal() 메서드를 사용하면 뷰를 표시하거나 숨기도록 클리핑 서클을 애니메이션할 수 있음.
            이 애니메이션은 Android5.0(API 21) 이상에서 사용할 수 있는 ViewAnimationUtils 클래스에 제공되어 있음.

            // previously invisible view
            val myView: View = findViewById(R.id.my_view)

            // Check if the runtime version is at least Lollipop
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // get the center for the clipping circle
                val cx = myView.width / 2
                val cy = myView.height / 2

                // get the final radius for the clipping circle
                val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

                // create the animator for this view (the start radius is zero)
                val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius)
                // make the view visible and start the animation
                myView.visibility = View.VISIBLE
                anim.start()
            } else {
                // set the view to invisible without a circular reveal animation below Lollipop
                myView.visibility = View.INVISIBLE
            }

            ViewAnimationUtils.createCircularReveal() 애니메이션에는 5개의 매개변수가 사용됨. 첫 번째 매개변수는 화면에서 숨기거나
            표시하려는 뷰. 그 다음 두 갸의 매개변수는 클리핑 서클 중심에 대한 x, y 좌표. 일반적으로 이 것이 뷰의 중심이 되지만, 사용자가 터치한
            지점도 사용할 수 있음. 이 경우 사용자가 선택한 위치에서 애니메이션이 시작됨. 네번째 매개변수는 클리핑 서클의 시작 반지름.

            // previously visible view
            val myView: View = findViewById(R.id.my_view)

            // Check if the runtime version is at least Lollipop
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                // get the center for the clipping circle
                val cx = myView.width / 2
                val cy = myView.height / 2

                // get the initial radius for the clipping circle
                val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

                // create the animation (the final radius is zero)
                val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0f)

                // make the view invisible when the animation is done
                anim.addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        myView.visibility = View.INVISIBLE
                    }
                })

                // start the animation
                anim.start()
            } else {
                // set the view to visible without a circular reveal animation below Lollipop
                myView.visibility = View.VISIBLE
            }

            이 경우 클리핑 서클의 초기 반지름 크기가 뷰와 같도록 설정되었기 때문에 애니메이션이 시작되기 전에 뷰가 표시됨. 최종 반지름이
            0으로 설정되어 애니메이션이 끝나면 뷰가 숨겨짐. 애니메이션이 완료되면 뷰의 가시성을 INVISIBLE로 설정할 수 있도록 애니메이션에
            리스너를 추가하는 것이 중요.

            2. 크로스페이드 애니메이션
            디졸브 애니메이션이라고도 함. 하나의 View 또는 ViewGroup을 점진적으로 페이드 아웃하는 동시에 다른 View 또는 ViewGroup을 페이드 인 함. 이 애니메이션은 앱에서
            콘텐츠 또는 뷰를 전환하려는 경우 유용함. 여기에 표시된 크로스페이드 애니메이션에서는 Android3.1(API 12) 이상에 사용 가능한 ViewPropertAnimator를 사용함.
                -뷰 만들기
                먼저, 크로스페이드할 뷰를 두 개 만들어야 함.
                <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">

                    <ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
                        android:id="@+id/content"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent">

                        <TextView style="?android:textAppearanceMedium"
                            android:lineSpacingMultiplier="1.2"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="@string/lorem_ipsum"
                            android:padding="16dp" />

                    </ScrollView>

                    <ProgressBar android:id="@+id/loading_spinner"
                        style="?android:progressBarStyleLarge"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center" />

                </FrameLayout>

                -크로스페이드 애니메이션 설정
                    1. 크로스페이드할 뷰의 멤버 변수를 만들어야 함. 나중에 애니메이션 중에 뷰를 수정할 때 필요.
                    2. 페이드 인되는 뷰의 가시성을 GONE으로 설정. 이렇게 하면 뷰가 레이아웃 공간을 차지하지 않고 레이아웃 계산애서 생략되어 처리 속도가 빨라짐.
                    3. 멤버 변수에서 config_shortAnimTime 시스템 속성을 캐시함. 이 속성은 애니메이션의 '짧은' 표준 재생 시간을 정의함.
                    이러한 재생 시간은 섬세한 애니메아션 또는 자주 발생하는 애니메이션에서 이상적. 원한다면 config_longAnimTime 및 config_mediu,AnimTime도 사용할 수 있음.

                class CrossfadeActivity : Activity() {

                    private lateinit var contentView: View
                    private lateinit var loadingView: View
                    private var shortAnimationDuration: Int = 0

                    ...

                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        setContentView(R.layout.activity_crossfade)

                        contentView = findViewById(R.id.content)
                        loadingView = findViewById(R.id.loading_spinner)

                        // Initially hide the content view.
                        contentView.visibility = View.GONE

                        // Retrieve and cache the system's default "short" animation time.
                        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
                    }
                    ...
                }

                -뷰 크로스페이드
                이제 뷰가 적절히 설정되었으므로 다음 단계를 통해 뷰를 크로스페이드함.
                    1. 페이드 인되는 뷰의 알파 값을 0으로 설정하고 가시성을 VISIBLE로 설정함.
                    2. 페이드 인되는 뷰는 알파 값을 0에서 1로 애니메이션함. 페이드 아웃되는 뷰는 알파 값을 1에서 0으로 애니메이션 함.
                    3. Animator.AnimatorListener에서 onAnimatorEnd()를 사용해 페이드 아웃된 뷰의 가시성을 GONE으로 설정함.
                    알파 값이 0이더라도 뷰의 가시성을 GONE으로 설정하면 뷰가 레이아웃 공간을 차지하지 않고 레이아웃 계산에서 생략되어 처리 속도가 빨라짐.

                class CrossfadeActivity : Activity() {

                    private lateinit var contentView: View
                    private lateinit var loadingView: View
                    private var shortAnimationDuration: Int = 0

                    ...

                    private fun crossfade() {
                        contentView.apply {
                            // Set the content view to 0% opacity but visible, so that it is visible
                            // (but fully transparent) during the animation.
                            alpha = 0f
                            visibility = View.VISIBLE

                            // Animate the content view to 100% opacity, and clear any animation
                            // listener set on the view.
                            animate()
                                    .alpha(1f)
                                    .setDuration(shortAnimationDuration.toLong())
                                    .setListener(null)
                        }
                        // Animate the loading view to 0% opacity. After the animation ends,
                        // set its visibility to GONE as an optimization step (it won't
                        // participate in layout passes, etc.)
                        loadingView.animate()
                                .alpha(0f)
                                .setDuration(shortAnimationDuration.toLong())
                                .setListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        loadingView.visibility = View.GONE
                                    }
                                })
                    }
                }



            3. 카드플립 애니메이션
            뒤집히는 카드를 에뮬레이션하는 애니메이션을 표시해 콘텐츠 뷰 간에 애니메이션함. 여기에 표시된 카드플립 애니메이션에서는 Android3.0(API 11) 이상에서 사용할 수 있는
            FragmentTransaction을 사용함.

                -Animation 개체 만들기
                애니메이션을 만들려면 총 4개의 애니메이터가 필요함. 두 개의 애니메이터는 카드 앞면이 애니메이션되어 왼쪽으로 뒤집혔다가 왼쪽에서 넘어오도록 하는 데 필요함.
                카드 뒷면이 애니메이션되어 오른쪽에서 넘어왔다가 오른쪽으로 뒤집히도록 하는 데에도 두 개의 애니메이터가 필요함.

                card_flip_left_in.xml
                <set xmlns:android="http://schemas.android.com/apk/res/android">
                    <!-- Before rotating, immediately set the alpha to 0. -->
                    <objectAnimator
                        android:valueFrom="1.0"
                        android:valueTo="0.0"
                        android:propertyName="alpha"
                        android:duration="0" />

                    <!-- Rotate. -->
                    <objectAnimator
                        android:valueFrom="-180"
                        android:valueTo="0"
                        android:propertyName="rotationY"
                        android:interpolator="@android:interpolator/accelerate_decelerate"
                        android:duration="@integer/card_flip_time_full" />

                    <!-- Half-way through the rotation (see startOffset), set the alpha to 1. -->
                    <objectAnimator
                        android:valueFrom="0.0"
                        android:valueTo="1.0"
                        android:propertyName="alpha"
                        android:startOffset="@integer/card_flip_time_half"
                        android:duration="1" />
                </set>

                card_flip_left_out.xml
                <set xmlns:android="http://schemas.android.com/apk/res/android">
                    <!-- Rotate. -->
                    <objectAnimator
                        android:valueFrom="0"
                        android:valueTo="180"
                        android:propertyName="rotationY"
                        android:interpolator="@android:interpolator/accelerate_decelerate"
                        android:duration="@integer/card_flip_time_full" />

                    <!-- Half-way through the rotation (see startOffset), set the alpha to 0. -->
                    <objectAnimator
                        android:valueFrom="1.0"
                        android:valueTo="0.0"
                        android:propertyName="alpha"
                        android:startOffset="@integer/card_flip_time_half"
                        android:duration="1" />
                </set>

                card_flip_right_in.xml
                <set xmlns:android="http://schemas.android.com/apk/res/android">
                    <!-- Before rotating, immediately set the alpha to 0. -->
                    <objectAnimator
                        android:valueFrom="1.0"
                        android:valueTo="0.0"
                        android:propertyName="alpha"
                        android:duration="0" />

                    <!-- Rotate. -->
                    <objectAnimator
                        android:valueFrom="180"
                        android:valueTo="0"
                        android:propertyName="rotationY"
                        android:interpolator="@android:interpolator/accelerate_decelerate"
                        android:duration="@integer/card_flip_time_full" />

                    <!-- Half-way through the rotation (see startOffset), set the alpha to 1. -->
                    <objectAnimator
                        android:valueFrom="0.0"
                        android:valueTo="1.0"
                        android:propertyName="alpha"
                        android:startOffset="@integer/card_flip_time_half"
                        android:duration="1" />
                </set>

                card_flip_right_out.xml
                <set xmlns:android="http://schemas.android.com/apk/res/android">
                    <!-- Rotate. -->
                    <objectAnimator
                        android:valueFrom="0"
                        android:valueTo="-180"
                        android:propertyName="rotationY"
                        android:interpolator="@android:interpolator/accelerate_decelerate"
                        android:duration="@integer/card_flip_time_full" />

                    <!-- Half-way through the rotation (see startOffset), set the alpha to 0. -->
                    <objectAnimator
                        android:valueFrom="1.0"
                        android:valueTo="0.0"
                        android:propertyName="alpha"
                        android:startOffset="@integer/card_flip_time_half"
                        android:duration="1" />
                </set>

                -뷰 만들기
                카드의 각 면은 원하는 콘텐츠를 포함할 수 있는 별개의 레이아웃. 두 레이아웃은 나중에 애니메이션할 프래그먼트에 사용됨.

                <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:orientation="vertical"
                        android:background="#a6c"
                        android:padding="16dp"
                        android:gravity="bottom">

                        <TextView android:id="@android:id/text1"
                            style="?android:textAppearanceLarge"
                            android:textStyle="bold"
                            android:textColor="#fff"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="@string/card_back_title" />

                        <TextView style="?android:textAppearanceSmall"
                            android:textAllCaps="true"
                            android:textColor="#80ffffff"
                            android:textStyle="bold"
                            android:lineSpacingMultiplier="1.2"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="@string/card_back_description" />

                </LinearLayout>

                <ImageView xmlns:android="http://schemas.android.com/apk/res/android"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:src="@drawable/image1"
                    android:scaleType="centerCrop"
                    android:contentDescription="@string/description_image_1" />

                -프래그먼트 만들기
                카드의 앞면과 뒷면을 위한 프래그먼트 클래스를 만듦. 이러한 클래스는 각 프래그먼트의 onCreateView() 메서드에서 이전에 만든 레이아웃을 반환.
                그러면 카드를 표시하고자 하는 상위 활동에서 이 프래그먼트의 인스턴스를 만들 수 있음.

                class CardFlipActivity : FragmentActivity() {
                    ...
                    /**
                     * A fragment representing the front of the card.
                     */
                    class CardFrontFragment : Fragment() {

                        override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?
                        ): View = inflater.inflate(R.layout.fragment_card_front, container, false)
                    }

                    /**
                     * A fragment representing the back of the card.
                     */
                    class CardBackFragment : Fragment() {

                        override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?
                        ): View = inflater.inflate(R.layout.fragment_card_back, container, false)
                    }
                }

                -카드 플립 애니메이션
                상위 액티비티 내에 있는 프래그먼트를 표시. 이를 위해 액티비티에 관한 레이아웃을 먼저 만듦.

                <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:id="@+id/container"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" />

                액티비티 코드에서 콘텐츠 뷰를 방금 만든 레이아웃으로 설정. 또한 액티비티가 생성돠면 가본 프래그먼트를 표시하는 것이 좋음.

                class CardFlipActivity : FragmentActivity() {

                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        setContentView(R.layout.activity_activity_card_flip)
                        if (savedInstanceState == null) {
                            supportFragmentManager.beginTransaction()
                                    .add(R.id.container, CardFrontFragment())
                                    .commit()
                        }
                    }
                    ...
                }

                카드 앞면이 표시되었으므로 적절한 시간에 플립 애니메이션으로 카드 뒷면을 표시할 수 있음. 카드의 다른 면을 보여주는 메서드를 만듦.
                -프래그먼트 전환을 위해 이전에 만든 맞춤 애니메이션을 설정
                -현재 표시된 프래그먼트를 새 프래그먼트로 바꾸고 이 이벤트를 이전에 만든 마줌 애니메이션으로 애니메이션함.
                -이전에 표시된 프래그먼트를 프래그먼트 백 스택에 추가함. 그러면 사용자가 뒤로 버튼을 누르면 카드가 다시 뒤집힘.

                class CardFlipActivity : FragmentActivity() {
                    ...

                    private fun flipCard() {
                        if (showingBack) {
                            supportFragmentManager.popBackStack()
                            return
                        }

                        // Flip to the back.

                        showingBack = true

                        // Create and commit a new fragment transaction that adds the fragment for
                        // the back of the card, uses custom animations, and is part of the fragment
                        // manager's back stack.

                        supportFragmentManager.beginTransaction()

                                // Replace the default fragment animations with animator resources
                                // representing rotations when switching to the back of the card, as
                                // well as animator resources representing rotations when flipping
                                // back to the front (e.g. when the system Back button is pressed).
                                .setCustomAnimations(
                                        R.animator.card_flip_right_in,
                                        R.animator.card_flip_right_out,
                                        R.animator.card_flip_left_in,
                                        R.animator.card_flip_left_out
                                )

                                // Replace any fragments currently in the container view with a
                                // fragment representing the next page (indicated by the
                                // just-incremented currentPage variable).
                                .replace(R.id.container, CardBackFragment())

                                // Add this transaction to the back stack, allowing users to press
                                // Back to get to the front of the card.
                                .addToBackStack(null)

                                // Commit the transaction.
                                .commit()
                    }
                }

        */
        /*
        -확대/축소 애니메이션으로 뷰 확대
        뷰를 미리보기 이미지에서 화면을 가둑 채우는 크기 이미지로 애니멩션하는 사진 갤러리 같은 앱에 유용.

            -뷰 만들기
            확대/축소하려는 콘텐츠의 큰 버전과 작은 버전이 포함된 레이아웃 파일을 만듦.

            <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:id="@+id/container"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                <LinearLayout android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical"
                    android:padding="16dp">

                    <ImageButton
                        android:id="@+id/thumb_button_1"
                        android:layout_width="100dp"
                        android:layout_height="75dp"
                        android:layout_marginRight="1dp"
                        android:src="@drawable/thumb1"
                        android:scaleType="centerCrop"
                        android:contentDescription="@string/description_image_1" />

                </LinearLayout>

                <!-- This initially-hidden ImageView will hold the expanded/zoomed version of
                     the images above. Without transformations applied, it takes up the entire
                     screen. To achieve the "zoom" animation, this view's bounds are animated
                     from the bounds of the thumbnail button above, to its final laid-out
                     bounds.
                     -->

                <ImageView
                    android:id="@+id/expanded_image"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:visibility="invisible"
                    android:contentDescription="@string/description_zoom_touch_close" />

            </FrameLayout>

            -확대/축소 애니메이션 설정
            레이아웃을 적용했으면 확대/축소 애니메이션을 트리거하는 이벤트 핸들러를 설정.

            class ZoomActivity : FragmentActivity() {

                // Hold a reference to the current animator,
                // so that it can be canceled mid-way.
                private var currentAnimator: Animator? = null

                // The system "short" animation time duration, in milliseconds. This
                // duration is ideal for subtle animations or animations that occur
                // very frequently.
                private var shortAnimationDuration: Int = 0

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_zoom)

                    // Hook up clicks on the thumbnail views.

                    val thumb1View: View = findViewById(R.id.thumb_button_1)
                    thumb1View.setOnClickListener({
                        zoomImageFromThumb(thumb1View, R.drawable.image1)
                    })

                    // Retrieve and cache the system's default "short" animation time.
                    shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
                }
                ...
            }

            -뷰 확대/축소
            필요에 따라 일반 크기의 뷰에서 확대/축소된 뷰로 애니메이션. 대개 일반 크기의 뷰 경계에서 확대된 크기의 뷰 경계로 애니메이션해야 함.
            1. 고해상도 이미지를 숨겨진 확대된 ImageView에 할당. 편의를 위해 UI 스레드에 큰 이미지 리소스를 로드. UI 스레드에서는 차단을
            방지하기 위해 이 로드를 별도의 스레드에서 수행한 다음, UI 스레드에 비트맵을 설정할 수 있음. 비트멥이 화면 크기보다 크지 않은 것이 이상적.
            2. ImageView의 시작 경계와 끝 경계를 계산
            3. 시작 경계에서 끝 경계까지 4개의 각 위치 지정 및 크기 조정 속성 X, Y, SCALE_X, SCALE_Y를 동시에 애니메이션함. 이러한
            4개의 애니메이션은 동시에 시작될 수 있도록 AnimatorSet에 추가됨.
            4. 이미지가 확대된 상태에서 사용자가 화면을 터치하는 경우 유사한 애니메이션을 반대로 실행해 다시 축소.
            ImageView에 View.OnClickListener를 추가해 이 작업을 수행할 수 있도록 함. ImageView는 클릭시 미리보기 이미지 크기로 다시 축소하고
            공개된 상태를 GONE으로 설정해 숨김.

            private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
                // If there's an animation in progress, cancel it
                // immediately and proceed with this one.
                currentAnimator?.cancel()

                // Load the high-resolution "zoomed-in" image.
                val expandedImageView: ImageView = findViewById(R.id.expanded_image)
                expandedImageView.setImageResource(imageResId)

                // Calculate the starting and ending bounds for the zoomed-in image.
                // This step involves lots of math. Yay, math.
                val startBoundsInt = Rect()
                val finalBoundsInt = Rect()
                val globalOffset = Point()

                // The start bounds are the global visible rectangle of the thumbnail,
                // and the final bounds are the global visible rectangle of the container
                // view. Also set the container view's offset as the origin for the
                // bounds, since that's the origin for the positioning animation
                // properties (X, Y).
                thumbView.getGlobalVisibleRect(startBoundsInt)
                findViewById<View>(R.id.container)
                        .getGlobalVisibleRect(finalBoundsInt, globalOffset)
                startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
                finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

                val startBounds = RectF(startBoundsInt)
                val finalBounds = RectF(finalBoundsInt)

                // Adjust the start bounds to be the same aspect ratio as the final
                // bounds using the "center crop" technique. This prevents undesirable
                // stretching during the animation. Also calculate the start scaling
                // factor (the end scaling factor is always 1.0).
                val startScale: Float
                if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
                    // Extend start bounds horizontally
                    startScale = startBounds.height() / finalBounds.height()
                    val startWidth: Float = startScale * finalBounds.width()
                    val deltaWidth: Float = (startWidth - startBounds.width()) / 2
                    startBounds.left -= deltaWidth.toInt()
                    startBounds.right += deltaWidth.toInt()
                } else {
                    // Extend start bounds vertically
                    startScale = startBounds.width() / finalBounds.width()
                    val startHeight: Float = startScale * finalBounds.height()
                    val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
                    startBounds.top -= deltaHeight.toInt()
                    startBounds.bottom += deltaHeight.toInt()
                }

                // Hide the thumbnail and show the zoomed-in view. When the animation
                // begins, it will position the zoomed-in view in the place of the
                // thumbnail.
                thumbView.alpha = 0f
                expandedImageView.visibility = View.VISIBLE

                // Set the pivot point for SCALE_X and SCALE_Y transformations
                // to the top-left corner of the zoomed-in view (the default
                // is the center of the view).
                expandedImageView.pivotX = 0f
                expandedImageView.pivotY = 0f

                // Construct and run the parallel animation of the four translation and
                // scale properties (X, Y, SCALE_X, and SCALE_Y).
                currentAnimator = AnimatorSet().apply {
                    play(ObjectAnimator.ofFloat(
                            expandedImageView,
                            View.X,
                            startBounds.left,
                            finalBounds.left)
                    ).apply {
                        with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                        with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                        with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
                    }
                    duration = shortAnimationDuration.toLong()
                    interpolator = DecelerateInterpolator()
                    addListener(object : AnimatorListenerAdapter() {

                        override fun onAnimationEnd(animation: Animator) {
                            currentAnimator = null
                        }

                        override fun onAnimationCancel(animation: Animator) {
                            currentAnimator = null
                        }
                    })
                    start()
                }

                // Upon clicking the zoomed-in image, it should zoom back down
                // to the original bounds and show the thumbnail instead of
                // the expanded image.
                expandedImageView.setOnClickListener {
                    currentAnimator?.cancel()

                    // Animate the four positioning/sizing properties in parallel,
                    // back to their original values.
                    currentAnimator = AnimatorSet().apply {
                        play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                            with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                            with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                            with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                        }
                        duration = shortAnimationDuration.toLong()
                        interpolator = DecelerateInterpolator()
                        addListener(object : AnimatorListenerAdapter() {

                            override fun onAnimationEnd(animation: Animator) {
                                thumbView.alpha = 1f
                                expandedImageView.visibility = View.GONE
                                currentAnimator = null
                            }

                            override fun onAnimationCancel(animation: Animator) {
                                thumbView.alpha = 1f
                                expandedImageView.visibility = View.GONE
                                currentAnimator = null
                            }
                        })
                        start()
                    }
                }
            }

     */
    /*물리학 기반 모션
    자연스러움을 위해 애니메이션에 실제 물리학을 적용해야 함.
    이러한 동작을 제공하기 위해 "Android Support 라이브러리"에 물리학 법칙에 따라 애니메이션의 동작을 제어하는,
    물리학 기반 애니메이션 API가 있음.
    물리학을 기반으로 하지 않는 애니메이션은 상당히 정적이고 재생시간이 고정되어 있음.
    타겟이 변경되면 변경 시 애니메이션을 취소하고 새 값을 새 시작 값으로 설정해 애니메이션을 재구성한 다음 타겟 값을 추가해야 함.
    반면 DynamicAnimation 같은 물리학 기반 애니메이션 API로 제작된 애니메이션은 물리력으로 구동.
    일반적인 물리학 기반 애니메이션*/
        /*
        -스프링 애니메이션
        물리학 기반 모션은 강제로 구동됨. 스프링력은 상호작용과 모션을 안내하는 힘. 스프링력에는 감쇠 및 강성 같은 속성이 있음.
        스프링 기반 애니메이션에서 값과 속도는 각 프레임에 적용되는 스프링력에 따라 계산됨.
        앱의 애니메이션이 한 방향으로만 느려지게 하려면 마찰 기반 플링 애니메이션을 대신 사용해야 함.
            -스프링 애니메이션 수명주기
            SpringForce 클래스를 사용하면 스프링의 강성, 감쇠 비율 및 최종 위치를 맞춤 설정할 수 있음.
            애니메이션이 시작도면 바로 스프링에서 각 프레임의 애니메이션 값과 속도를 강제로 업데이트함.
            애니메이션은 스프링력이 평형 상태가 될 때까지 계속됨.

            -스프링 애니메이션 빌드
            애플리케이션의 스프링 애니메이션을 빌드하는 일반적인 단계는 다음과 같음.
            1. support 라이브러리 추가: 스프링 애니메이션 클래스를 사용하려면 프로젝트에 라이브러리를 추가해야 함.
                1. 앱 모듈의 build.gradle 파일을 열음.
                2. 라이브러리를 dependencies 섹션에 추가.
                      dependencies {
                          def dynamicanimation_version = "1.0.0"
                          implementation 'androidx.dynamicanimation:dynamicanimation:$dynamicanimation_version'
                      }

            2. 스프링 애니메이션 만들기: 기본 단계에서는 SpringAnimation 클래스의 인스턴스를 생성하고 모션 동작 매개변수를 설정함.
            SpringAnimation 클래스를 사용하면 객체의 스프링 애니메이션을 만들 수 있음. 스프링 애니메이션을 빌드하려면 SpringAnimation 클래스의
            인스턴스를 만들고, 객체, 애니메이션화할 객체의 속성, 애니메이션이 정지할 선택적 최종 스프링 위치를 제공해야 함.
            val springAnim = findViewById<View>(R.id.imageView).let { img ->
                // Setting up a spring animation to animate the view’s translationY property with the final
                // spring position at 0.
                SpringAnimation(img, DynamicAnimation.TRANSLATION_Y, 0f)
            }
            스프링 기반 애니메이션은 보기 객체의 실제 속성을 변경하여 화면에서 보기를 애니메이션화할 수 있음. 시스템에서 사용할 수 있는 보기는 다음과 같음.
            ALPHA: 보기의 알파 투명도를 나타냄. 값은 기본적으로 1(불투명)이며 값 0은 완전 투명(표시되지 않음)을 나타냄.
            TRANSLATION_X, TRANSLATION_Y, TRANSLATION_Z: 이 속성은 레이아웃 컨테이너에서 설정한 왼쪽 좌표, 위쪽 좌표 및 높이로부터의
            델타 값으로 보기의 위치를 제어함.
                TRANSLATION_X는 왼쪽 좌표를 나타냄.
                TRANSLATION_Y는 위쪽 좌표를 나타냄.
                TRANSLATION_Z은 높이를 기준으로 보기의 깊이를 나타냄.
            ROTATION, ROTATION_X, ROTATION_Y: 이 속성은 중심점을 기준으로 3D, 2D에서 회전을 제어함.
            SCROLL_X, SCROLL_Y: 이 속성은 소스 왼쪽 및 위쪽 가장자리의 스크롤 오프셋을 나타냄. 페이지가 얼마나 스크롤되는지의 측면에서 위치도 표시.
            SCALE_X, SCALE_Y: 이 속성은 중심점을 기준으로 보기의 2D 크기 조정을 제어함.
            X, Y, Z: 컨테이너에서 보기의 최종 위치를 나타내는 기본 유틸리티 속성.
                X는 왼쪽 값과 TRANSLATION_X의 합계
                Y는 왼쪽 값과 TRANSLATION_Y의 합계
                Z는 왼쪽 값과 TRANSLATION_Z의 합계

            3. (선택사항)리스너 등록: 리스널르 등록해 애니메이션 수명주기 변경 및 애니메이션 값 업데이트를 관찰.
            DynamicAnimation 클래스에서는 두 개의 리스너, OnAnimationUpdateListener 및 OnAnimationEndListener을(를) 제공함.
            이러한 리스너는 애니메이션 값이 변경되고 애니메이션이 끝날 때 애니메이션의 업데이트를 수신 대기함.
            (업데이트 리스너는 애니메이션 값 변경 시 프레임당 업데이트가 필요할 떼만 등록. 업데이트 리스너에서는 애니메이션이 별도의 스레드에서 실행되지 않도록 함.)

                -OnAnimationUpdateListener
                여러 보기를 애니메이션화하여 체인된 애니메이션을 만들려면 현재 보기의 속성이 변경될 때마다 콜백을 받도록 OnAnimationUpdateListener을(를) 설정할 수 있음.
                콜백은 현재 보기의 속성에서 발생하는 변경사항을 기반으로 스프링 위치를 업데이트하도록 다른 보기에 알림. 리스너를 등록하려면 다음 단계를 실행함.

                    1. addUpdateListener() 메서드를 호출하고 리스너를 애니매이션에 연결함.
                    (애니메이션을 시작하기 전에 업데이트 리스너를 등록해야 함. 그러나 애니메이션 값이 변경될 때 프레임별 업데이트가 필요한 경우에만 업데이트 리스너를 등록해야 함.
                    업데이트 리스너에서는 애니메이션이 별도의 스레드에서 실행되지 않도록 함.)
                    2. 호출자에게 현재 객체의 변경에 관해 알리도록 onAnimationUpdate() 메서드를 재정의함. 다음 샘플 코드에서는 OnAnimationUpdateListener의 전반적인 사용을 보여줌.
                    // Setting up a spring animation to animate the view1 and view2 translationX and translationY properties
                    val (anim1X, anim1Y) = findViewById<View>(R.id.view1).let { view1 ->
                        SpringAnimation(view1, DynamicAnimation.TRANSLATION_X) to
                                SpringAnimation(view1, DynamicAnimation.TRANSLATION_Y)
                    }
                    val (anim2X, anim2Y) = findViewById<View>(R.id.view2).let { view2 ->
                        SpringAnimation(view2, DynamicAnimation.TRANSLATION_X) to
                                SpringAnimation(view2, DynamicAnimation.TRANSLATION_Y)
                    }

                    // Registering the update listener
                    anim1X.addUpdateListener { _, value, _ ->
                        // Overriding the method to notify view2 about the change in the view1’s property.
                        anim2X.animateToFinalPosition(value)
                    }

                    anim1Y.addUpdateListener { _, value, _ -> anim2Y.animateToFinalPosition(value) }

                -OnAnimationEndListener
                OnAnimationEndListener에서는 애니메이션의 끝을 알림. 애니메이션이 평형 상태가 되거나 취소될 때마다 콜백을 수신하도록 리스너를 설정할 수 있음.
                리스너를 등록하려면 다음 단계를 수행함.
                    1.addEndListener() 메서드를 호출하고 리스너를 애니메이션에 연결함.
                    2.애니메이션이 평형 상태가 되거나 취소될 때마다 알림을 받도록 onAnimationEnd() 메서드를 재정의함.

            4. (선택사항)리스너 삭제: 더 이상 사용되지 않는 리스너를 삭제.
            애니메이션 업데이트 콜백 및 애니메이션 종료 콜백 수신을 중지하려면 각각 removeUpdateListener() 및 removeEndListener() 메서드를 호출함.

            5. (선택사항)시작 값 설정: 애니메이션 시작 값을 맞춤설정함.
            애니메이션의 시작 값을 설정하려면 setStartValue() 메서드를 호출하고 애니메이션의 시작 값을 전달함.
            시작 값을 설정하지 않으면 애니메이션에서 객체의 현재 속성 값을 시작 값으로 사용함.

            6. (선택사항)값 범위 설정: 촤소 및 최대 범위 내로 값을 제한하도록 애니메이션 값 범위를 설정.
            속성 값을 특정 범위로 제한하려면 최소 및 최대 애니메이션 값을 설정할 수 있음.
            또한 알파(0 ~ 1)와 같은 고유 범위가 있는 속성을 애니메이션할 때도 범위를 제어하는 데 도움이 됨.
            최소값을 설정하려면 setMinValue() 메서드를 호출하고 속성의 최소값을 전달함.
            최대값을 설정하려면 setMaxValue() 메서드를 호출하고 속성의 최대값을 전달함.
            두 메서드 모두 값이 설정된 애니메이션을 반환합니다.(시작 값을 설정하고 애니메이션 값 범위를 정의한 경우 시작 값이 최소값 및 최대값 범위에 있는지 확인함.)

            7. (선택사항)시작 속도 설정: 애니메이션의 시작 속도를 설정.
            시작 속도를 통해 애니메이션 시작 시 애니메이션 속성이 변경되는 속도를 정의함.
            기본 시작 속도는 초당 픽셀로 설정됨. 터치 동작 속도를 사용하거나 고정 값을 시작 속도로 사용하여 속도를 설정할 수 있음.
            고정 값을 제공하도록 선택하면 초당 dp 값을 정의한 다음 초당 픽셀로 변환하는 것이 좋음. 초당 dp로 값을 정의하면 속도가 밀도 및 폼 팩터에 독립적일 수 있음.
            값을 초당 픽셀로 변환하는 방법에 관한 자세한 내용은 초당 dp를 초당 픽셀로 변환 섹션을 참조.
            속도를 설정하려면 setStartVelocity() 메서드를 호출하고 속도를 초당 픽셀 단위로 전달함. 이 메서드는 속도가 설정된 스프링력 객체를 반환함.
            (GestureDetector.OnGestureListener 또는 VelocityTracker 클래스 메서드를 사용하여 터치 동작의 속도를 검색하고 계산)
            findViewById<View>(R.id.imageView).also { img ->
                SpringAnimation(img, DynamicAnimation.TRANSLATION_Y).apply {
                    …
                    // Compute velocity in the unit pixel/second
                    vt.computeCurrentVelocity(1000)
                    val velocity = vt.yVelocity
                    setStartVelocity(velocity)
                }
            }
                -초당 dp를 초당 픽셀로 변환
                스프링의 속도는 초당 픽셀 단위여야 함. 고정 값을 속도의 시작으로 제공하도록 선택하면 초당 dp 값을 제공한 다음 초당 픽셀로 변환함.
                변환하려면 TypedValue 클래스의 applyDimension() 메서드를 사용함.
                val pixelPerSecond: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpPerSecond, resources.displayMetrics)

            8. (선택사항)스프링 속성 설정: 스프링의 감쇠비와 강성을 설정.
            SpringForce 클래스에서는 감쇠비 및 강성과 같은 각 스프링 속성의 getter와 setter 메서드를 정의함.
            스프링 속성을 설정하려면 스프링력 객체를 검색하거나 속성을 설정할 수 있는 맞춤 스프링력을 만드는 것이 중요함.
            맞춤 스프링력을 만드는 데 관한 자세한 내용은 맞춤 스프링력 만들기 섹션을 참조.
            (모든 setter 메서드에서 스프링력 객체를 반환하므로, setter 메서드를 사용하는 동안 메서드 체인을 만들 수 있습니다.)

                -감쇠비
                감쇠비는 스프링 진동이 점진적으로 감소하는 것을 나타냄.
                감쇠비를 사용하여 한 탄성에서 다음 탄성으로 신속하게 진동이 감소되는 방식을 정의할 수 있음. 스프링을 감쇠시키는 방법은 다음 4가지가 있음.
                    -감쇠비가 1보다 크면 과도 감쇠가 발생. 그러면 객체가 신속하게 정지 위치로 돌아감.
                    -임계 감쇠는 감쇠비가 1일 때 발생. 그러면 최단 시간 내에 객체가 정지 위치로 돌아감.
                    -부족 감쇠는 감쇠비가 1 미만일 때 발생. 그러면 객체가 여러 번 정지 위치를 지나친 다음 점진적으로 정지 위치에 도달함.
                    -비감쇠는 감쇠비가 0일 때 발생. 그러면 객체가 영구적으로 진동할 수 있음.
                스프링에 감쇠비를 추가하려면 다음 단계를 완료함.
                    1. getSpring() 메서드를 호출하여 감쇠비를 추가할 스프링을 검색.
                    2. setDampingRatio() 메서드를 호출하고 스프링에 추가할 감쇠비를 전달. 메서드는 감쇠비가 설정된 스프링력 객체를 반환.
                (감쇠비는 음수가 아닌 숫자여야 함. 감쇠비를 0으로 설정하면 스프링이 정지 위치에 도달하지 않음. 즉, 영구적으로 진동.)
                시스템에서 사용할 수 있는 감쇠비 상수는 다음과 같습니다.
                    -DAMPING_RATIO_HIGH_BOUNCY
                    -DAMPING_RATIO_MEDIUM_BOUNCY(기본)
                    -DAMPING_RATIO_LOW_BOUNCY
                    =DAMPING_RATIO_NO_BOUNCY

                findViewById<View>(R.id.imageView).also { img ->
                    SpringAnimation(img, DynamicAnimation.TRANSLATION_Y).apply {
                        …
                        //Setting the damping ratio to create a low bouncing effect.
                        spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                        …
                    }
                }
                    -강성
                    강성을 통해 스프링의 강도를 측정하는 스프링 상수를 정의. 강성 스프링은 스프링이 정지 위치에 있지 않을 때 연결된 객체에 더 큰 힘을 가함.
                    스프링에 강성을 추가하려면 다음 단계를 완료.
                        1. getSpring() 메서드를 호출하여 강성을 추가할 스프링을 검색합니다.
                        2. setStiffness() 메서드를 호출하여 스프링에 추가할 강성 값을 전달합니다. 이 메소드는 강성이 설정된 스프링력 객체를 반환합니다.
                        (강성은 양수여야 함.)
                    시스템에서 사용할 수 있는 강성 상수는 다음과 같음.
                        -STIFFNESS_HIGH
                        -STIFFNESS_MEDIUM(기본)
                        -STIFFNESS_LOW
                        -STIFFNESS_VERY_LOW

            9. (선택사항)맞춤 스프링 만들기: 기본 스프링을 사용하지 않거나 애니메이션 전체에서 공통 스프링을 사용하려면 맞춤 스프링을 만듦.
            findViewById<View>(R.id.imageView).also { img ->
                SpringAnimation(img, DynamicAnimation.TRANSLATION_Y).apply {
                    …
                    //Setting the spring with a low stiffness.
                    spring.stiffness = SpringForce.STIFFNESS_LOW
                    …
                }
            }
            기본 스프링력을 사용하는 대신 맞춤 스프링력을 만들 수 있음.
            맞춤 스프링력을 사용하면 여러 스프링 애니메이션에 동일한 스프링력 인스턴스를 공유할 수 있음.
            스프링력을 만든 후 감쇠비 및 강성과 같은 속성을 설정할 수 있음.
                1. SpringForce 객체를 만듦.
                    SpringForce force = new SpringForce();
                2. 각 메서드를 호출하여 속성을 할당. 메서드 체인도 만들 수 있음.
                    force.setDampingRatio(DAMPING_RATIO_LOW_BOUNCY).setStiffness(STIFFNESS_LOW);
                3. setSpring() 메서드를 호출하여 스프링을 애니메이션으로 설정.
                    setSpring(force);

            10.애니메이션 시작: 스프링 애니메이션을 시작.
            스프링 애니메이션을 시작할 수 있는 방법은 start()을(를) 호출하거나 animateToFinalPosition() 메서드를 호출하는 식의 두 가지 방법이 있음.
            두 메서드 모두 기본 스레드에서 호출해야 함.
            animateToFinalPosition() 메소드를 통해 다음 두 가지 작업을 완료함.
                -스프링의 최종 위치를 설정.
                -애니메이션이 시작되지 않았으면 애니메이션을 시작.
            이 메서드에서 스프링의 마지막 위치를 업데이트하고 필요한 경우 애니메이션을 시작하므로 언제든지 이 메서드를 호출하여 애니메이션 과정을 변경할 수 있음.
            예를 들어 연쇄 스프링 애니메이션에서 한 보기의 애니메이션은 다른 보기에 따라 달라짐. 이러한 애니메이션에서는 animateToFinalPosition() 메서드를 사용하는 것이 더 편리.
            연쇄 스프링 애니메이션에서 이 메서드를 사용하면 다음에 업데이트하려는 애니메이션이 현재 실행 중인지 신경 쓰지 않아도 됨.
            animateToFinalPosition() 메서드를 사용하려면 animateToFinalPosition() 메서드를 호출하고 스프링의 정지 위치를 전달함.
            setFinalPosition() 메서드를 호출해서도 스프링의 정지 위치를 설정할 수 있음.
            start() 메서드에서는 속성 값을 즉시 시작 값으로 설정하지 않음. 속성 값은 각 애니메이션이 깜박일 때 변경되며 그리기 단계 이전에 발생.
            따라서 값이 즉시 설정되는 것처럼 변경사항이 다음 프레임에 반영.

            findViewById<View>(R.id.imageView).also { img ->
                SpringAnimation(img, DynamicAnimation.TRANSLATION_Y).apply {
                    …
                    //Starting the animation
                    start()
                    …
                }
            }

            11.(선택사항)애니메이션 취소: 사용자가 갑자기 앱을 종료하거나 보기가 보이지 않으면 애니메이션 취소.
            애니메이션을 취소하거나 애니메이션의 끝으로 건너뛸 수 있음. 애니메이션을 취소하거나 애니메이션의 끝으로 건너뛰어야 하는 경우는 사용자 상호작용에 따라 애니메이션을 즉시 종료해야 하는 경우.
            주로 사용자가 앱을 갑자기 종료하거나 보기가 표시되지 않는 경우.
            애니메이션을 종료하는 데 사용할 수 있는 메서드는 두 가지가 있음.
                -cancel() 메서드에서는 값이 있는 위치에서 애니메이션을 종료.
                -skipToEnd() 메서드에서는 애니메이션을 최종 값까지 건너뛴 다음 종료.
            애니메이션을 종료하려면 먼저 스프링의 상태를 확인해야 함. 상태가 감쇠되지 않으면 애니메이션은 정지 위치에 도달할 수 없음. 스프링의 상태를 확인하려면 canSkipToEnd() 메서드를 호출.
            스프링이 감쇠되면 메서드에서 true를 반환하고, 감쇠되지 않으면 false를 반환. 스프링의 상태를 알면 skipToEnd() 메서드 또는 cancel() 메서드를 사용하여 애니메이션을 종료할 수 있음.
            cancel() 메서드는 기본 스레드에서만 호출해야 함.
        */
        /*
        -플링 애니메이션
        객체의 속도에 비례하는 마찰력을 사용함. 이 마찰력을 사용해 객체의 속성을 애니메이션화하고 애니메아션을 점차적으로 종료.
        초기 운동량은 대부분 동작 속도에서 받으며 점차 속도가 느려짐. 애니메이션의 속도가 낮아서 기기 화면에 눈에 띄는 변화가 없으면 애니메이션 종료.
            -지원 라이브러리 추가
            물리학 기반 지원 라이브러리를 사용하려면 다음과 같이 프로젝트에 support 라이브러리를 추가해야 함.
                1. 앱 모듈의 build.gradle 파일열 열음.
                2. 지원 라이브러리를 dependencies 섹션에 추가.
                dependencies {
                    implementation 'com.android.support:support-dynamic-animation:28.0.0'
                }

            -플링 애니메이션 만들기
            FlingAnimation 클래스를 사용하면 객체의 플링 애니메이션을 만들 수 있음. 플링 애니메이션을 만들려면
            FlingAnimation 객체의 인스턴스를 만들고 애니메이션화할 객체 및 객체의 속성을 제공.
            val fling = FlingAnimation(view, DynamicAnimation.SCROLL_X)

            -속도 설정
            시직 속도에 따라 애니메이션 시작 시 애니메이션 속성이 변경되는 속도가 정의됨. 기본 시작 속도는 초당 0 픽셀로 설정.
            따라서 애니메이션이 즉시 종료되지 않도록 시작 속도를 정의해야 함.
            고정 값을 시작 속도로 사용하거나 터치 동작의 속도를 기준으로 설정할 수 있음. 고정 값을 제공하도록 선택하면 초당 dp 값을
            정의한 다음 초당 픽셀로 변환해야 함. 초당 dp로 값을 정의하면 속도가 기기의 밀도와 폼 팩터에 독립적이 됨. 시작 속도를
            초당 픽셀로 변환하는 방법에 관해 자세한 내용은 스프링 애니메이션의 초당 dp를 초당 픽셀로 변환 섹션을 참조.
            속도를 설정하려면 setStartVelocity() 메서드를 호출하고 속도를 초당 픽셀 단위로 전달. 이 메서드에서는 속도가 설정된 플링
            객체를 변환.
            (GestureDetector.OnGestureListener 및 VelocityTracker 클래스를 사용하여 각각 터치 동작의 속도를 검색하고 계산)
                -애니메이션 값 범위 설정
                속성 값을 특정 범위로 제한하려면 최소 및 최대 애니메이션 값을 설정할 수 있음. 이 범위 제어는 알파와 같은 고유 범위가 있는
                속성을 애니메이션화할 때 특히 유용.
                (플링 애니메이션의 값이 최소값 또는 최대값에 도달하면 애니메이션이 종료됨.)
                최소값과 최대값을 설정하려면 각각 setMinvalue() 및 setMaxValue() 메서드를 호출함. 두 메서드 모두 사용자가 값을
                설정한 애니메이션 객체를 변환함.

                -마찰 설정
                setFriction() 메서드를 사용해 애니메이션의 마찰을 변경할 수 있음. 애니메이션에서 속도가 얼마나 빨리 감소하는지를 정의.
                (애니메이션 시작 시 마찰을 설정하지 않으면 애니메이션에서는 기본 마찰 값 1을 사용함.)
                이 메서드에서는 애니메이션에서 사용자가 제공한 마찰 값을 사용하는 객체를 반환.

                FlingAnimation(view, DynamicAnimation.SCROLL_X).apply {
                    setStartVelocity(-velocityX)
                    setMinValue(0f)
                    setMaxValue(maxScroll)
                    friction = 1.1f
                    start()
                }

                -최소 표시 변경 설정
                픽셀로 정의되지 않은 맞춤 속성을 애니메이션화할 때 사용자에게 표시되는 최소 애니메이션 값 변경을 설정해야 함. 애니멩션 종료에 적합
                한 임계값을 판별.
                DynamicAnimation.ViewProperty을 애니메이션화할 때 최소 표시 변경은 속성에서 파생되므로 이 메서드를 호출하지 않아도 됨.
                애니메이션의 최소 표시 변경을 설정하려면 setMinimumVisibleChange() 메서드를 호출하고 최소 표시 상수 중 하나 또는 맞춤 속성을
                계산하는 데 필요한 값을 전달. 이 값을 계산하는 방법에 관해 자세한 내용은 최소 표시 변경 값 계산 섹션을 참조.
                anim.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE
                (픽셀로 정의되지 않은 맞춤 속성을 애니메이션화할 때만 값을 전달해야 함.)
                    -최소 표시 변경 값 계산
                    맞춤 속성의 최소 표시 변경 값을 계산하려면 다음 수식을 사용.
                    최소 표시 변경 = 맞춤 속성 값의 범위 / 픽셀 단위의 애니메이션 범위

        */
    /*레이아웃 변경 애니메이션
    Android4.4(API 19) 이상에서는 현재 액티비티 또는 프래그먼트 내에서 레이아웃을 변경할 때 전환 프레임워크를 사용해 애니메이션을 만들 수 있음.
    시작 및 종료 레이아웃과 사용하려는 애니메이션을 파악해 실행함. 전체 UI를 교체하거나 일부 뷰만 이동/교체 할 수도 있음.
    시작 장면은 대개 현재 레이아웃에서 자동으로 결정되지만 시작 및 종료 레이아웃은 Scene에 각각 저장됨. 원하는 애니메이션 유형을 시스템에 알리는
    Transition을 만든 후 TransitionManager.go()를 호출. 그러면 시스템이 애니메이션을 실행해 레이아웃을 전환함.
    Transition 프레임워크를 사용해 단순히 시작 레이아웃과 종료 레이아웃을 제공해 UI에서 모든 종류의 모션을 애니메이션으로 보여줄 수 있음.
    개발자가 원하는 애니메이션 유형을 선택할 수 있으며, 프레임워크에서 시작 레이아웃까지 애니메이션으로 보여주는 방법을 알아냄.
    Transition 프레임워크에는 다음 기능이 포함되어 있음.
        -그룹 레벨 애니메이션: 보기 계층의 모든 보기에 하나 이상의 애니메이션 효과를 적용.
        -내장형 애니메이션: 페이드 아웃 또는 이동과 같은 일반 효과용으로 미리 정의된 애니메이션을 사용함.
        -리소스 파일 지원: 레이아웃 리소스 파일에서 계층 구조와 내장형 애니매이션을 로드함.
        -수명 주기 콜백: 애니메이션과 계층 구조 변경 프로세스 제어 기능을 제공하는 콜백을 받음.
        (동일한 활동 내에서 레이아웃 간 전환을 빌드하는 방법을 설명함. 사용자가 액티비티 사이를 이동하면 애니메이션을 사용해 액티비티 시작을 대신 참조)
    레이아웃이 다른 레이아웃으로 전환될 때 이를 애니메이션으로 보여주는 기본 프로세스는 다음과 같음.
        1. 시작 레이아웃과 종료 레이아웃 둘 다의 Scene 객체를 만듦. 그러나 시작 레이아웃의 장면은 종종 현재 레이아웃을 기반으로 자동으로 결정.
        2. Transition 객체를 만들어 원하는 애니메이션 유형을 정의.
        3. TransitionManager.go()를 호출하면 시스템에서 애니메이션을 실행해 레이아웃을 전환.
     */
        /*
        -Scene 만들기
        모든 보기와 속성 값을 비롯해 보기 계층 구조의 상태를 저장. 전환 프레임워크를 통해 시작 장면과 종료 장면 사이에 애니메이션을 실행할 수 있음.
        레이아웃 리소스 파일 또는 코드의 보기 그룹에서 장면을 만들 수 있음. 그러나 전환할 시작 장면은 종종 현재 UI를 기반으로 자동으로 결정.
        장면을 변경할 때 실행하는 고유 작업도 정의할 수 있음.
        (프레임워크에서는 장면 없이 전환 작용에 설명한 대로 장면을 사용하지 않고 단일 뷰 계층 구조의 변경사항을 애니메이션화할 수 있음. 그러나
        전환과 작업을 하려면 장면을 이해해야 함.)
            -레이아웃 리소스에서 장면 만들기
            레이아웃 리소스 파일에서 직접 Scene 인스턴스를 만들 수 있음. 파일의 뷰 계층 구조가 주로 정적이면 이 기법을 사용.
            결과 장면은 Scene 인스턴스를 만든 시점의 뷰 계층 구조 상태를 나타냄. 뷰 계층 구조를 변경하면 장면을 다시 만들어야 함.
            프레임워크가 파일에 있는 전체 뷰 계층 구조에서 장면을 생성하므로, 레이아웃 파일의 일부 장면을 만들 수는 없음.
            레이아웃 리소스 파일에서 Scene 인스턴스를 만들려면 레이아웃에서 장면 루트를 ViewGroup 인스턴스로 검색하고 장면 루트와 장면의 뷰
            계층 구조가 포함된 레이아웃 파일의 리소스 ID로 Scene.getSceneForLayout) 함수를 호출.

                -장면 레이아웃 정의
                이 섹션의 나머지 부분에 있는 코드 스니펫에서는 장면 루트 요소가 같은 두 개의 서로 다른 장면을 만드는 방법을 보여줌.
                관련 없는 여러 Scene 객체를 서로 관련되어 있다고 암시하지 않고 로드할 수 있다는 점도 보여줌.

                res/layout/activity_main.xml
                <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:id="@+id/master_layout">
                    <TextView
                        android:id="@+id/title"
                        ...
                        android:text="Title"/>
                    <FrameLayout
                        android:id="@+id/scene_root">
                        <include layout="@layout/a_scene" />
                    </FrameLayout>
                </LinearLayout>

                이 레이아웃 정의에는 장면 루트의 하위 레이아웃과 텍스트 필드가 포함되어 있음. 첫 번째 장면의 레이아웃은 기본 레이아웃 파일에 포함되어 있음.
                프레임워크는 전체 레이아웃 파일만 장면에 로드하라 수 있기 때문에, 앱을 초기 사용자 인터페이스의 일부로 표시하고 장면에 로드할 수 있음.

                res/layout/a_scene.xml
                <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:id="@+id/scene_container"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" >
                    <TextView
                        android:id="@+id/text_view1"
                        android:text="Text Line 1" />
                    <TextView
                        android:id="@+id/text_view2"
                        android:text="Text Line 2" />
                </RelativeLayout>

                res/layout/another_scene.xml
                <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:id="@+id/scene_container"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" >
                    <TextView
                        android:id="@+id/text_view2"
                        android:text="Text Line 2" />
                    <TextView
                        android:id="@+id/text_view1"
                        android:text="Text Line 1" />
                </RelativeLayout>

                -레이아웃 장면 생성
                두 상대 레이아웃의 정의를 만든 다음 각 정의의 장면을 얻을 수 있음. 그러므로 나중에 두 개의 UI 구성을 서로 전환할 수 있음.
                장면을 얻으려면 레이아웃 리소스 ID와 장면 루트의 참조가 필요.

                val sceneRoot: ViewGroup = findViewById(R.id.scene_root)
                val aScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene, this)
                val anotherScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.another_scene, this)

                이제 앱에는 뷰 계층 구조에 기반한 두 개의 Scene 객체가 있음. 두 장면 모두 res/layout/activity_main.xml의 Frame
                Layout 요소로 정의된 장면 루트를 사용.

            -코드에 장면 만들기
            ViewGroup 객체에서 코드에 Scene 인스턴스를 만들 수도 있음. 개발자의 코드에서 직접 뷰 계층 구조를 수정하거나 동적으로 생성할 때 이 기법을 사용.
            코드의 뷰 계층 구조에서 장면을 만들려면 Scene(sceneRoot, viewHierarchy) 생성자를 사용. 이 생성자 호출은 레이아웃 파일을 이미 확장했을 때
            Scene.getSceneForLayout() 함수를 호출하는 것과 동일.

            val sceneRoot = someLayoutElement as ViewGroup
            val viewHierarchy = someOtherLayoutElement as ViewGroup
            val scene: Scene = Scene(sceneRoot, viewHierarchy)

            -장면 작업을 만들기
            프레임워크를 사용하면 장면을 시작하거나 종료할 때 시스템에서 실행하는 맞춤 장면 작업을 정의할 수 있음. 프레임워크에서 자동으로 장면 사이의 변경사항을
            애니메이션으로 보여주므로, 대부분의 경우 맞춤 장면 작업을 정의할 필요가 없음. 장면 작업은 다음과 같은 케이스를 처리하는 데 유용.
                -같은 계층 구조에 없는 보기를 애니메이션으로 보여줌. 장면 시작 및 종료 작업을 사용하여 장면을 시작하고 종료하는 보기를 애니메이션으로 보여줄 수 있음.
                -ListView 객체와 같이 전환 프레임워크에서 자동으로 애니메이션화할 수 없는 뷰를 애니메이션화. 자세한 내용은 제한사항을 참조.
            맞춤 장면 작업을 제공하려면 작업을 Runnable 객체로 정의하여 Scene.setExitAction() 또는 Scene.setEnterAction() 함수에 전달.
            프레임워크에서는 전환 애니메이션을 실행하기 전에 시작 장면에서 setExitAction() 함수를 호출하고 전환 애니메이션 실행 후 종료 장면에서 setEnterAction() 함수를 호출.
            (시작 장면과 종료 장면 사이에 데이터를 전달하는 데 장면 작업을 사용하면 안됨. 자세한 내용은 전환 수명 주기 콜백 정의를 참조.)
        */
        /*전환 적용
        Transition 프레임워크에서는 Transition 객체를 사용하여 장면 사이의 애니메이션 스타일을 나타냄. 내장된 여러 서브 클래스를 사용해 Transition을 인스턴스화하거나
        자체 전환을 정의할 수 있음. 그런 다음 종료 Scene 및 Transition을 TransitionManager.go()에 전달하여 장면 사이에 애니메이션을 실행할 수 있음.
        전환 수명 주기는 활동 수명 주기와 비슷하며, 프레임워크에서 애니메이션 시작과 완료 사이를 모니터링하는 전환 상태를 나타냄.
        중요한 수명 주기 상태에서, 프레임워크는 여러 다른 전환 단계의 사용자 인터페이스를 조정하기 위해 구현할 수 있는 콜백 함수를 호출.

            -전환 만들기
            변경하려는 시작 장면과 종료 장면을 정의했다면 애니메이션을 정의하는 Transition 객체를 만들어야 함.
            프레임워크를 사용하면 리소스 파일에서 내장형 전환을 지정하고 개발자 코드에서 확장하거나 개발자 코드에서 직접 내장형 전환의 인스턴스를 만들 수 있음.

                -리소스 파일에서 전환 인스턴스 만들기
                이 기법을 사용하면 활동의 코드를 변경하지 않아도 전환 정의를 수정할 수 있음.
                이 기법은 여러 전환 지정에 표시된 대로 애플리케이션 코드에서 복합 전환 정의를 구분하는 데도 유용.
                    1. 프로젝트에 res/transition/ 디렉터리를 추가.
                    2. 이 디렉터리에 새로운 XML 리소스 파일을 만듦.
                    3. 내장형 전환 중 하나의 XML 노드를 추가.

                res/transition/fade_transition.xml
                <fade xmlns:android="http://schemas.android.com/apk/res/android" />

                var fadeTransition: Transition =
                TransitionInflater.from(this)
                                  .inflateTransition(R.transition.fade_transition)

                -개발자의 코드에서 전환 인스턴스 만들기
                이 기법은 개발자 코드의 사용자 인터페이스를 수정할 때 동적으로 전환 객체를 만드는 데 또는 매개변수가 적거나 없는 간단한 내장형 전환 인스턴스를 만드는 데 유용.
                내장된 전환 인스턴스를 만들려면 Transition 클래스의 서브클래스에 있는 공개 생성자 중 하나를 호출. 예를 들어 다음 코드 스니펫은 Fade 전환 인스턴스를 만듦.

                var fadeTransition: Transition = Fade()

            -전환 적용
            일반적으로 사용자 작업과 같은 이벤트에 응답하여 여러 개의 서로 다른 보기 계층 구조 간에 변경하기 위해 전환을 적용합니다. 예를 들어 다음과 같은 검색 앱을 고려.
            사용자가 검색어를 입력하고 검색 버튼을 클릭하면, 검색 버튼을 페이드 아웃하고 검색 결과를 페이드 인하는 전환을 적용하는 동안 앱이 결과 레이아웃을 나타내는 장면으로 변경.
            활동의 이벤트에 응답하여 전환을 적용하는 동안 장면을 변경하려면 다음 스니펫과 같이 애니메이션에 사용할 종료 장면과 전환 인스턴스와 함께 TransitionManager.go() 클래스 함수를 호출.

            TransitionManager.go(endingScene, fadeTransition)

            전환 인스턴스에서 지정한 애니메이션을 실행하는 동안 프레임워크에서 종료 장면의 보기 계층 구조로 장면 루트에 있는 보기 계층 구조를 변경.
            시작 장면은 마지막 전환의 종료 장면. 이전 전환이 없으면 사용자 인터페이스의 현재 상태에서 자동으로 시작 장면이 결정.

            -특정 타겟 보기 선택
            프레임워크에서는 기본적으로 시작 장면과 종료 장면에 있는 모든 보기에 전환을 적용. 경우에 따라 장면에 있는 보기의 하위 세트에만 애니메이션을 적용할 수 있음.
            프레임워크를 사용하면 애니메이션으로 보여줄 특정 보기를 선택할 수 있음.전환 중에 애니메이션으로 보여주는 각 보기는 타겟이라고 함. 장면과 연결된 뷰 계층 구조의 일부인 타겟만 선택할 수 있음.
            타겟 목록에서 뷰를 하나 이상 삭제하려면 전환을 시작하기 전에 removeTarget() 메서드를 호출. 개발자가 지정하는 뷰만 타겟 목록에 추가하려면 addTarget() 함수를 호출.

            -여러 전환 지정
            애니메이션의 효과를 극대화하려면 장면 사이에 발생하는 변경 유형과 애니메이션을 일치시켜야 함.
            전환 프레임워크를 사용하면 개별 내장형 전환 또는 맞춤 전환 그룹을 포함하는 전환 세트에 애니메이션 효과를 결합할 수 있으므로 여러 개의 애니메이션을 선택해도 됨.
            XML의 전환 컬렉션에서 전환 세트를 정의하려면 res/transitions/ 디렉터리에 리소스 파일을 만들고 transitionSet 요소 아래에 전환을 나열.

            <transitionSet xmlns:android="http://schemas.android.com/apk/res/android"
                android:transitionOrdering="sequential">
                <fade android:fadingMode="fade_out" />
                <changeBounds />
                <fade android:fadingMode="fade_in" />
            </transitionSet>

            전환 세트를 코드의 TransitionSet 객체로 확장하려면 활동에서 TransitionInflater.from() 함수를 호출.
            TransitionSet 클래스는 Transition 클래스에서 확장되므로 다른 Transition 인스턴스와 마찬가지로 전환 관리자와 함께 사용할 수 있음.

            -장면 없이 전환 적용
            사용자 인터페이스를 수정하는 데는 보기 계층 구조를 변경하는 방법 외에도 다른 방법이 있음. 현재 계층 구조에서 하위 보기를 추가, 수정 및 제거하여 변경할 수도 있음.
            검색 항목 필드와 검색 아이콘을 표시하는 레이아웃으로 시작. 결과를 표시하도록 사용자 인터페이스를 변경하려면 ViewGroup.removeView() 함수를 호출하여 사용자가
            검색 버튼을 클릭할 때 검색 버튼을 삭제하고 ViewGroup.addView() 함수를 호출하여 검색 결과를 추가.
            대안으로 거의 동일한 두 개의 계층 구조가 있으면 이 방법을 사용할 수 있음. 사용자 인터페이스의 사소한 차이 때문에 두 개의 개별 레이아웃 파일을 만들고 유지관리할 필요 없이,
            개발자가 코드에서 수정하는 보기 계층 구조가 포함된 하나의 레이아웃 파일을 사용할 수 있음.
            현재 보기 계층 구조에서 이 방식으로 변경하면 장면을 만들지 않아도 됨. 대신 지연된 전환을 사용하여 보기 계층 구조의 두 상태 간에 전환을 만들어 적용할 수 있음.
            이 전환 프레임워크 기능은 현재 보기 계층 구조 상태부터 시작하여, 보기의 변경사항을 기록하고, 시스템이 사용자 인터페이스를 수정할 때 변경사항을 애니메이션으로 보여주는 전환을 적용.
            단일 뷰 계층 구조에 지연된 전환을 만들려면 다음 단계를 따릅니다.
                1. 전환을 트리거하는 이벤트가 발생하면 변경하려는 모든 뷰의 상위 뷰와 사용할 전환을 제공하는 TransitionManager.beginDelayedTransition() 함수를 호출.
                프레임워크에서 하위 뷰의 현재 상태와 속성 값을 저장.
                2. 사용 사례에 맞게 필요한 대로 하위 보기를 변경. 프레임워크에서는 하위 보기와 속성의 변경사항을 기록.
                3. 시스템에서 변경사항에 따라 사용자 인터페이스를 수정하면 프레임워크에서 원래 상태와 새 상태 간 변경사항을 애니메이션으로 보여줌.

            res/layout/activity_main.xml
            <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:id="@+id/mainLayout"
                android:layout_width="match_parent"
                android:layout_height="match_parent" >
                <EditText
                    android:id="@+id/inputText"
                    android:layout_alignParentLeft="true"
                    android:layout_alignParentTop="true"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content" />
                ...
            </RelativeLayout>

            MainActivity
            setContentView(R.layout.activity_main)
            val labelText = TextView(this).apply {
                text = "Label"
                id = R.id.text
            }
            val rootView: ViewGroup = findViewById(R.id.mainLayout)
            val fade: Fade = Fade(Fade.IN)
            TransitionManager.beginDelayedTransition(rootView, mFade)
            rootView.addView(labelText)

            -전환 수명 주기 콜백 정의
            전환 수명 주기는 활동 수명 주기와 비슷. TransitionManager.go() 함수 호출과 애니메이션 완료 사이의 시간에 프레임워크에서 모니터링하는 전환 상태를 나타냄.
            중요한 수명 주기 상태에서 프레임워크는 TransitionListener 인터페이스에서 정의한 콜백을 호출.




         */
    /*액티비티 간 애니메이션
    Android5.0(API 21) 이상에서는 액티비티 간 전환되는 애나메이션을 만들 수도 있음.
     */