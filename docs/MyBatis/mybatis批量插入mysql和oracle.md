<insert id="insertBatch" parameterType="java.util.List">
	INSERT INTO MSG_USER_REF_ROLE(
	<include refid="column_list" />
	)
	values
	<foreach collection="list" item="item" index="index" separator=",">
		(#{item.roleId},#{item.userId})
	</foreach>
</insert>
	
<insert id="insertBatch" parameterType="java.util.List">
	INSERT INTO MSG_USER_REF_ROLE(
	<include refid="column_list" />
	)
	<foreach collection="list" item="item" index="index" separator="union all">
		select  #{item.roleId},#{item.userId} from dual  
	</foreach>
</insert>
