import java.util.Random;
import java.util.Scanner;

public class SecondMainClass {
    public static void main(String[] args){
        int betMarble;
        int userMarble = 10;
        int comMarble = 10;
        int ranMarble;
        String isOdd;
        String answer;
        boolean isMyTurn = true;

        // 랜덤 설정
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        Scanner sc = new Scanner(System.in); // Scanner Class 가져오기
        // 인트로
        System.out.println("홀짝 게임");
        System.out.println("두 명의 참가자가 각각 10개의 구슬 보유");
        System.out.println("10개의 구슬을 다 잃을 시 사망");
        System.out.println("게임 시작");


        while (true){
            // 구슬 베팅
            while(true){
                if(isMyTurn) {
                    System.out.print("배팅할 구슬의 갯수 : ");
                    betMarble = sc.nextInt(); // 숫자 스캔 후 입력 받기
                    if (betMarble > userMarble) {
                        System.out.println("구슬 갯수 초과 ");
                    } else if (betMarble > comMarble) {
                        System.out.println("상대 구슬 갯수 모자람");
                    } else {
                        System.out.println("배팅된 구슬 갯수 : " + betMarble);
                        break;
                    }
                }else{
                    betMarble = random.nextInt(Math.min(comMarble, userMarble)) + 1;
                    break;
                }
            }

            // 난수로 홀 / 짝 생성
            ranMarble = random.nextInt(Math.min(comMarble, userMarble)) * random.nextInt(Math.min(comMarble, userMarble)) % 10 + 1;

            if(isMyTurn) {
                // User 정답 입력
                System.out.print("홀 / 짝 예측( 홀 / 짝 ) : ");
            }else{
                // User가 문제 내기
                System.out.print("문제 내기 : ");
            }
            answer = sc.next();


            isOdd = ranMarble % 2 == 0 ? "짝" : "홀";
            System.out.println("컴퓨터가 선택한 구슬 갯수 : " + ranMarble + isOdd);

            if(!isMyTurn){
                betMarble *= -1;
                System.out.println("상대방 배팅 : " + ranMarble);
            }


            // 정답 / 오답 판별 후 구슬 더하거나 빼기
            if(answer.equals(isOdd)){
                System.out.println("정답");
                userMarble += betMarble;
                comMarble -= betMarble;
            }else {
                System.out.println("오답");
                userMarble -= betMarble;
                comMarble += betMarble;
            }

            isMyTurn = !isMyTurn;
            System.out.println();
            System.out.println("현재 구슬 갯수 : " + userMarble);
            System.out.println("상대 구슬 갯수 : " + comMarble);

            // 게임 종료
            if(userMarble <= 0){
                System.out.println("짐");
                break;
            } else if (userMarble >= 20) {
                System.out.println("이김");
                break;
            }
        }
    }
}
