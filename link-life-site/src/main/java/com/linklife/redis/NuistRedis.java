package com.linklife.redis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.linklife.domain.model.AreaRankModel;
import com.linklife.obj.Redisobj;

@Component
public class NuistRedis {

	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	public enum StaticArea {
		EAST, CENTER, WEST
	}

	// public void putAndGet(){
	// redisTemplate.opsForHash().put("user","name","张三");
	// Object name = redisTemplate.opsForHash().get("user","name");
	// System.out.println(name);
	// }

	public void saveAreaRankModel(final AreaRankModel obj, StaticArea area, int index) {
		String areaStr = "";
		switch (area) {
		case EAST:
			areaStr = "east";
			break;
		case CENTER:
			areaStr = "center";
			break;
		case WEST:
			areaStr = "west";
			break;

		default:
			break;
		}

		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hMSet(key, hashes);
				set(redisTemplate.getStringSerializer().serialize("nuist." + areaStr + index),
						redisTemplate.getStringSerializer().serialize(obj.getPassword()));
				return null;
			}
		});
	}

	public Redisobj getAreaRankModel(final String name) {
		return redisTemplate.execute(new RedisCallback<Redisobj>() {
			@Override
			public Redisobj doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + name);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String password = redisTemplate.getStringSerializer().deserialize(value);
					Redisobj obj = new Redisobj();
					obj.setName(name);
					obj.setPassword(password);
					return obj;
				}
				return null;
			}
		});
	}
}
