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