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