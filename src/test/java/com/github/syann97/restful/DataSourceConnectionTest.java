package com.github.syann97.restful;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataSourceConnectionTest {

	@Autowired
	private DataSource dataSource;

	@Test
	void testDataSourceConnection() throws Exception {
		// DataSource 객체가 null이 아닌지 확인
		assertThat(dataSource).isNotNull();

		// DataSource를 통해 데이터베이스 연결을 가져옴
		try (Connection connection = dataSource.getConnection()) {
			// 연결이 null이 아닌지, 유효한지 확인
			assertThat(connection).isNotNull();
			assertThat(connection.isValid(1)).isTrue(); // 1초 내에 연결이 유효한지 확인

			System.out.println("✅ 데이터베이스 연결 테스트 성공!");
			System.out.println("연결 URL: " + connection.getMetaData().getURL());
			System.out.println("사용자명: " + connection.getMetaData().getUserName());
		} catch (Exception e) {
			System.err.println("❌ 데이터베이스 연결 테스트 실패!");
			e.printStackTrace();
			throw e; // 예외를 다시 던져서 테스트 실패로 만듦
		}
	}
}