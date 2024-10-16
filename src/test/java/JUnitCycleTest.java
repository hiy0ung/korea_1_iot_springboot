import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll
    static void beforeAll() {
        // 전체 테스트를 시작하기 전에 1회 실행
        // - static 키워드 사용 이유
        // : 객체를 생성하지 않고도 호출될 수 있음 (static은 인스턴스화(객체화) X)
        // : 클래스가 로드될 때 한 번만 실행

        System.out.println("@BeforeAll");
    }

    // 테스트 케이스를 시작하기 전마다 실행
    // : public 설정 (인스턴스마다 사용하는 거라서 static X, public 사용)
    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    // 전체 테스트를 마치고 종료하기 전에 1회 실행하기 때문에
    // static 메서드로 선언
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    // 테스트 케이스를 종료하기 전마다 실행
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}

// @BeforeAll 클래스 레벨 설정
// @BeforeEach > @Test > @AfterEach 가 테스트 개수만큼 반복
// @AfterAll 클래스 레벨 정리
