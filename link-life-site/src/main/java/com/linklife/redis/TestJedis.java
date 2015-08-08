package com.linklife.redis;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.linklife.obj.Redisobj;

@Component
public class TestJedis {

	@Autowired
	protected RedisTemplate redisTemplate;


	// public void putAndGet(){
	// redisTemplate.opsForHash().put("user","name","张三");
	// Object name = redisTemplate.opsForHash().get("user","name");
	// System.out.println(name);
	// }
	@SuppressWarnings("unchecked")
	public void test( final Redisobj obj ) {

		//RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
		// HashMapper<Redisobj, String, String> hashMapper = new
		// DecoratingStringHashMapper<Redisobj>( new
		// JacksonHashMapper<Redisobj>( Redisobj.class ) );
		// java.util.Map<String, String> map = hashMapper.toHash(obj);
//		redisTemplate.execute( new RedisCallback<Object>() {
//
//			@Override
//			public Object doInRedis( RedisConnection connection ) throws DataAccessException {
//
//				byte[] key = redisSerializer.serialize( "test" );
//				BoundHashOperations<Serializable, byte[], byte[]> boundHashOperations = redisTemplate.boundHashOps( key );
//				boundHashOperations.put( redisSerializer.serialize( "name" ), redisSerializer.serialize( obj.getName() ) );
//				boundHashOperations.put( redisSerializer.serialize( "psww" ), redisSerializer.serialize( obj.getPassword() ) );
//				connection.hMSet( key, boundHashOperations.entries() );
//				return null;
//			}
//		} );

		HashOperations hashOper = redisTemplate.opsForHash();
		HashMap<String, Redisobj> map=new LinkedHashMap<String, Redisobj>();
		Redisobj obj1=new Redisobj();
		obj1.setName("asdasdgsfg");
		obj1.setPassword("211sdafsafd");
		map.put("11", obj);
		map.put("22", obj1);
		hashOper.putAll("user11", map);
		//ValueOperations<String, Redisobj> valueOper = redisTemplate.opsForValue();
	    //valueOper.set("user:" + "1",obj);
	}


	public void saveRedis( final Redisobj obj ) {

		redisTemplate.execute( new RedisCallback<Object>() {

			@Override
			public Object doInRedis( RedisConnection connection ) throws DataAccessException {

				connection.set( redisTemplate.getStringSerializer().serialize( "user.uid." + obj.getName() ),
						redisTemplate.getStringSerializer().serialize( obj.getPassword() ) );
				return null;
			}
		} );
	}


//	public Redisobj getRedis( final String name ) {
//
//		return redisTemplate.execute( new RedisCallback<Redisobj>() {
//
//			@Override
//			public Redisobj doInRedis( RedisConnection connection ) throws DataAccessException {
//
//				byte[] key = redisTemplate.getStringSerializer().serialize( "user.uid." + name );
//				if( connection.exists( key ) ) {
//					byte[] value = connection.get( key );
//					String password = redisTemplate.getStringSerializer().deserialize( value );
//					Redisobj obj = new Redisobj();
//					obj.setName( name );
//					obj.setPassword( password );
//					return obj;
//				}
//				return null;
//			}
//		} );
//	}
}
