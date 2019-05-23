
使用须知:

    mapper的XML文件里的判断字段时 , 如果是字段是 int/Integer/double/BigDecimal 时 , 则需手动删除非空串判断 .
    
    如 :
    
    _<if test="id != null and id != ''">_  
    
    如果id为int/Integer类型 , 则改为 : 
    
    _<if test="id != null">_  

    
    
    此外 , 支持的Java类型只有 : Integer/String/double/Timestamp/BigDecimal/Data . 
    
    其他类型默认为String . 
    
    
    **请在生成后仔细检查修改 .**
    
    
    
    其他所需Utils都存放在本项目的common路径下 .
    
    
    其他所需的jar如下 : 
    
        <properties>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
            <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
            <java.version>1.8</java.version>
            <druid-version>1.1.10</druid-version>
            <log4j-version>1.3.8.RELEASE</log4j-version>
            <fastjson-version>1.2.46</fastjson-version>
            <json-lib>2.4</json-lib>
            <commons-codec>1.10</commons-codec>
            <bcprov-jdk15on>1.60</bcprov-jdk15on>
            <commons-io>1.4</commons-io>
        </properties>
        
            
        <!-- Druid Jar -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid-version}</version>
        </dependency>
        <!--JSON-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastjson-version}</version>
        </dependency>
        <dependency>
            <groupId>net.sf.json-lib</groupId>
            <artifactId>json-lib</artifactId>
            <version>${json-lib}</version>
            <classifier>jdk15</classifier>
        </dependency>
        <!--文件上传-->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>${commons-io}</version>
        </dependency>
        <!--MD5加密-->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>${commons-codec}</version>
        </dependency>
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk15on</artifactId>
            <version>${bcprov-jdk15on}</version>
        </dependency>
        <!--日志-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j</artifactId>
            <version>${log4j-version}</version>
        </dependency>
        
        <!--Mybatis 分页-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.10</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    