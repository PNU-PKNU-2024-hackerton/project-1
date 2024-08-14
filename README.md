# project-1

- [PDF 주소](https://drive.google.com/file/d/1IADaL4rhexEzt1_XuIwWTvji5OURbN-p/view?usp=sharing)
- [유튜브 영상](https://youtu.be/W6tSslcCQOw)
- [배포 도메인 주소](https://anytime.fly.dev/)


## 목차
1. [개요](#개요)
2. [작업 방식 (PM & 컨트리뷰터)](#작업-방식-pm--컨트리뷰터)
   - [작업순서 예시](#작업순서-예시)
3. [디렉토리 구조](#디렉토리-구조)
4. [PR 템플릿](#pr-템플릿)
5. [코드 포매터 설정 방법](#코드-포매터-설정-방법)

## 개요
이 프로젝트는 `project-1`의 리포지토리입니다. 이 문서는 프로젝트 작업 방식, PR 템플릿, 코드 포매터 설정 방법 등을 설명합니다.

## 작업 방식 (PM & 컨트리뷰터)

| 작업순서 | PM 측 작업 방식       | 컨트리뷰터 측 작업 방식             |
|------|------------------|---------------------------|
| 1    | 리포 생성<br>README.md 생성<br>main 브랜치에 PR 룰 설정 |                               |
| 2    | 개발자 협력자로 초대        | 초대 수락                        |
| 3    | 이슈 생성<br>번호: 1번<br>라벨: enhancement<br>양수인: 개발자 1  | 1번 이슈 확인<br>or<br>양수인 없는 이슈 직접 양수  |
| 4    |                  | `git clone 깃헙주소 .`<br>리포지토리를 클론하여 로컬에 복사<br>or<br>`git pull origin main`<br>원격 저장소의 main 브랜치로부터 최신 변경 사항을 가져옴 |
| 5    |                  | `git fetch --prune`<br>원격 저장소의 변경 사항을 로컬로 가져오고, 삭제된 브랜치도 로컬에서 삭제 |
| 6    |                  | `git checkout -b enhancement/1`<br>새로운 브랜치를 생성하고 체크아웃 |
| 7    |                  | 작업 수행                     |
| 8    |                  | `git add .; git commit -m "작업내용"`<br>변경 사항을 스테이징하고 커밋 |
| 9    |                  | `git pull origin main --rebase`<br>원격 저장소의 main 브랜치로부터 변경 사항을 가져와 현재 브랜치에 리베이스 |
| 10   |                  | 작업 계속 수행                |
| 11   |                  | `git add .; git commit -m "작업내용"`<br>변경 사항을 스테이징하고 커밋 |
| 12   |                  | `git pull origin main --rebase`<br>원격 저장소의 main 브랜치로부터 변경 사항을 가져와 현재 브랜치에 리베이스 |
| 13   |                  | `git push origin enhancement/1`<br>현재 브랜치를 원격 저장소에 푸시<br>or<br>`git push origin enhancement/1 -f`<br>강제 푸시 |
| 14   |                  | 깃허브 접속, PR 생성 버튼 확인 |
| 15   | PR 생성<br>(enhancement/1 => main)<br>양수인과 리뷰어를 PM으로 지정 | |
| 16   | PR 확인, 리젝 또는 머지 결정 | 리젝 확인                      |
| 17   |                  | `git pull origin main --rebase`<br>원격 저장소의 main 브랜치로부터 변경 사항을 가져와 현재 브랜치에 리베이스 |
| 18   |                  | 작업 계속 수행                |
| 19   |                  | `git add .; git commit -m "작업내용"`<br>변경 사항을 스테이징하고 커밋 |
| 20   |                  | `git pull origin main --rebase`<br>원격 저장소의 main 브랜치로부터 변경 사항을 가져와 현재 브랜치에 리베이스 |
| 21   |                  | `git push origin enhancement/1`<br>현재 브랜치를 원격 저장소에 푸시<br>or<br>`git push origin enhancement/1 -f`<br>강제 푸시 |
| 22   | PR 승인            |                             |
| 23   | squash merge     |                             |
| 24   | 브랜치 삭제           |                             |
| 25   | 이슈 닫기            | 자동으로 닫힐 수 있음                |
| 26   |                  | `git checkout main`<br>main 브랜치로 체크아웃        |
| 27   |                  | `git pull origin main`<br>원격 저장소의 main 브랜치로부터 최신 변경 사항을 가져옴     |
| 28   |                  | `git branch -D enhancement/1`<br>로컬에서 enhancement/1 브랜치 삭제 |
| 29   |                  | `git fetch --prune`<br>원격 저장소의 변경 사항을 로컬로 가져오고, 삭제된 브랜치도 로컬에서 삭제        |
| 30   | 4번 내용부터 반복      |                             |

## 작업순서 예시

### 프로젝트 매니저가 해야 할 일
1. 리포 생성
2. README.md 생성
3. main 브랜치에 PR 룰 설정
4. 개발자 1을 협력자로 초대
5. 이슈 생성 (번호: 1번, 라벨: enhancement, 양수인: 개발자 1)
6. PR 확인 및 리젝 또는 머지 결정
7. squash merge
8. 브랜치 삭제
9. 이슈 닫기

### 개발자가 해야 할 일
1. 초대 수락
2. 이슈 확인 또는 양수인 없는 이슈 직접 양수
3. `git clone 깃헙주소 .` 또는 `git pull origin main`
4. `git checkout -b enhancement/1`
5. 작업 수행
6. `git add .; git commit -m "작업내용"`
7. `git pull origin main --rebase`
8. 작업 계속 수행
9. `git add .; git commit -m "작업내용"`
10. `git pull origin main --rebase`
11. `git push origin enhancement/1` 또는 `git push origin enhancement/1 -f`
12. 깃허브 접속, PR 생성
13. 리젝 확인 시 `git pull origin main --rebase` 후 작업 계속 수행 및 push
14. PR 승인 후 squash merge
15. `git checkout main`
16. `git pull origin main`
17. `git branch -D enhancement/1`
18. `git fetch --prune`
19. 새로운 이슈로 반복

## 디렉토리 구조
이 프로젝트는 IntelliJ, Gradle, Java, Spring 기반으로 개발되며, MVC 패턴을따릅니다. 아래는 권장하는 디렉토리 구조입니다

```bash
project-1
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── project1
│   │   │               ├── controller    # 컨트롤러 클래스
│   │   │               ├── service        # 서비스 클래스
│   │   │               ├── repository     # 레포지토리 클래스
│   │   │               ├── model          # 도메인 모델 클래스
│   │   │               ├── dto            # 데이터 전송 객체
│   │   │               └── config         # 설정 클래스
│   │   ├── resources
│   │   │   ├── static          # 정적 파일 (CSS, JavaScript, 이미지 등)
│   │   │   ├── templates       # 템플릿 파일 (Thymeleaf 등)
│   │   │   └── application.yml # Spring 설정 파일
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── project1
│                       ├── controller    # 컨트롤러 테스트
│                       ├── service        # 서비스 테스트
│                       ├── repository     # 레포지토리 테스트
│                       └── model          # 도메인 모델 테스트
│
├── build.gradle     # Gradle 빌드 파일
├── settings.gradle  # Gradle 설정 파일
└── README.md        # 프로젝트 설명 파일

```

## PR 템플릿

PR을 만들 때 이 템플릿을 사용해 주세요. 관련된 이슈를 자동으로 닫는 방법도 포함되어 있습니다.

```markdown
## #️⃣ 완료한 이슈

> ex) Closes #이슈번호, Closes #이슈번호

## 📝 작업 내용

> 이번 PR에서 작업한 내용을 간략히 설명해주세요 (이미지 첨부 가능)

### 스크린샷 (선택사항)

> 변경된 UI가 있다면 스크린샷을 첨부해주세요.

## 💬 리뷰 요구사항 (선택사항)

> 리뷰어가 특별히 봐주었으면 하는 부분이 있다면 작성해주세요
>
> ex) 메서드 XXX의 이름을 더 잘 짓고 싶은데 혹시 좋은 명칭이 있을까요?


## PR 템플릿

PR을 만들 때 이 템플릿을 사용해 주세요. 관련된 이슈를 자동으로 닫는 방법도 포함되어 있습니다.

```markdown
## #️⃣ 완료한 이슈

> ex) Closes #이슈번호, Closes #이슈번호

## 📝 작업 내용

> 이번 PR에서 작업한 내용을 간략히 설명해주세요 (이미지 첨부 가능)

### 스크린샷 (선택사항)

> 변경된 UI가 있다면 스크린샷을 첨부해주세요.

## 💬 리뷰 요구사항 (선택사항)

> 리뷰어가 특별히 봐주었으면 하는 부분이 있다면 작성해주세요
>
> ex) 메서드 XXX의 이름을 더 잘 짓고 싶은데 혹시 좋은 명칭이 있을까요?
```

## 코드 포매터 설정 방법

이 프로젝트에서는 네이버 Java 코드 포매터를 따릅니다. 이를 IntelliJ에 자동으로 적용하도록 설정하는 방법은 다음과 같습니다.

### IntelliJ에 네이버 코드 포매터 설정하기

#### 네이버 코드 포매터 XML 다운로드

네이버 코드 포매터 XML 파일을 [다운로드](https://github.com/naver/hackday-conventions-java/blob/master/rule-config/naver-intellij-formatter.xml)합니다.

#### IntelliJ에 XML 파일 가져오기

1. IntelliJ를 열고, 상단 메뉴에서 `File > Settings` (또는 `Preferences` on macOS)를 클릭합니다.
2. `Editor > Code Style > Java`로 이동합니다.
3. 우측 상단의 톱니바퀴 버튼을 클릭하고, `Import Scheme > IntelliJ IDEA Code Style XML`을 선택합니다.
4. 다운로드한 `rule-config/naver-intellij-formatter.xml` 파일을 선택합니다.

#### 코드 스타일 설정 적용

1. 적용된 코드 스타일을 선택하고, `OK` 버튼을 클릭하여 설정을 저장합니다.

### 프로젝트에 코드 스타일 설정 파일 추가

프로젝트 내에서 모든 기여자들이 동일한 코드 스타일을 사용할 수 있도록 코드 스타일 설정 파일을 프로젝트에 포함시킵니다.

### 코드 저장 시 자동 포매팅 및 import 최적화 설정

1. IntelliJ를 열고, 상단 메뉴에서 `File > Settings` (또는 `Preferences` on macOS`)를 클릭합니다.
2. `Tools > Actions on Save`로 이동합니다.
3. "Reformat code" 및 "Optimize imports" 옵션을 선택합니다.
4. `OK` 버튼을 클릭하여 설정을 저장합니다.

이제 IntelliJ에서 파일을 저장할 때마다 자동으로 코드 포맷팅과 import 최적화가 수행됩니다.
