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
    /*물리학 기반 모션
    자연스러움을 위해 애니메이션에 실제 물리학을 적용해야 함.
    이러한 동작을 제공하기 위해 "Android Support 라이브러리"에 물리학 법칙에 따라 애니메이션의 동작을 제어하는,
    물리학 기반 애니메이션 API가 있음.
    일반적인 물리학 기반 애니메이션
        -스프링 애니메이션
        -플링 애니메이션
    물리학을 기반으로 하지 않는 애니메이션은 상당히 정적이고 재생시간이 고정되어 있음.
    타겟이 변경되면 변경 시 애니메이션을 취소하고 새 값을 새 시작 값으로 설정해 애니메이션을 재구성한 다음 타겟 값을 추가해야 함.
    반면 DynamicAnimation 같은 물리학 기반 애니메이션 API로 제작된 애니메이션은 물리력으로 구동.
     */
    /*레이아웃 변경 애니메이션
    Android4.4(API 19) 이상에서는 현재 액티비티 또는 프래그먼트 내에서 레이아웃을 변경할 때 전환 프레임워크를 사용해 애니메이션을 만들 수 있음.
    시작 및 종료 레이아웃과 사용하려는 애니메이션을 파악해 실행함. 전체 UI를 교체하거나 일부 뷰만 이동/교체 할 수도 있움.
     */
    /*액티비티 간 애니메이션
    Android5.0(API 21) 이상에서는 액티비티 간 전환되는 애나메이션을 만들 수도 있음.
     */