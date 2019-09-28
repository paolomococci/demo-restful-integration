## GET example:
```
$ curl -X GET -i 'http://127.0.0.1:9090/api/vines'
HTTP/1.1 200 
Content-Type: application/hal+json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 28 Sep 2019 18:46:03 GMT

{
  "_embedded" : {
    "vines" : [ {
      "name" : "Sagrantino",
      "color" : "red",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:9090/api/vines/1"
        },
        "vines" : {
          "href" : "http://127.0.0.1:9090/api/vines"
        }
      }
    }, {
      "name" : "Sangiovese",
      "color" : "red",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:9090/api/vines/2"
        },
        "vines" : {
          "href" : "http://127.0.0.1:9090/api/vines"
        }
      }
    }, {
      "name" : "Ciliegiolo",
      "color" : "red",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:9090/api/vines/3"
        },
        "vines" : {
          "href" : "http://127.0.0.1:9090/api/vines"
        }
      }
    }, {
      "name" : "Pecorino",
      "color" : "white",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:9090/api/vines/4"
        },
        "vines" : {
          "href" : "http://127.0.0.1:9090/api/vines"
        }
      }
    }, {
      "name" : "Falanghina",
      "color" : null,
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:9090/api/vines/5"
        },
        "vines" : {
          "href" : "http://127.0.0.1:9090/api/vines"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/api/vines"
    }
  }
}
```

## POST example:

```
$ curl -i -v -X POST -H "Content-Type:application/json" -d '{"name":"Aglianico","color":"red"}' http://127.0.0.1:9090/api/vines
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> POST /api/vines HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 34
> 
* upload completely sent off: 34 out of 34 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 201 
HTTP/1.1 201 
< Location: http://127.0.0.1:9090/api/vines/6
Location: http://127.0.0.1:9090/api/vines/6
< Content-Type: application/hal+json;charset=UTF-8
Content-Type: application/hal+json;charset=UTF-8
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sat, 28 Sep 2019 18:58:28 GMT
Date: Sat, 28 Sep 2019 18:58:28 GMT

< 
{
  "name" : "Aglianico",
  "color" : "red",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/api/vines/6"
    },
    "vines" : {
      "href" : "http://127.0.0.1:9090/api/vines"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## PUT example:
```
$ curl -i -v -X PUT -H "Content-Type:application/json" -d '{"name":"Trebbiano","color":"white"}' http://127.0.0.1:9090/api/vines/6
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> PUT /api/vines/6 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 36
> 
* upload completely sent off: 36 out of 36 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 201 
HTTP/1.1 201 
< Location: http://127.0.0.1:9090/api/vines/6
Location: http://127.0.0.1:9090/api/vines/6
< Content-Type: application/hal+json;charset=UTF-8
Content-Type: application/hal+json;charset=UTF-8
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sat, 28 Sep 2019 19:03:14 GMT
Date: Sat, 28 Sep 2019 19:03:14 GMT

< 
{
  "name" : "Trebbiano",
  "color" : "white",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/api/vines/6"
    },
    "vines" : {
      "href" : "http://127.0.0.1:9090/api/vines"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## PATCH example:
```
$ curl -v -i -X  PATCH -H "Content-Type:application/json" -d '{"name":"Albana"}' http://127.0.0.1:9090/api/vines/6
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> PATCH /api/vines/6 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 17
> 
* upload completely sent off: 17 out of 17 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 201 
HTTP/1.1 201 
< Location: http://127.0.0.1:9090/api/vines/6
Location: http://127.0.0.1:9090/api/vines/6
< Content-Type: application/hal+json;charset=UTF-8
Content-Type: application/hal+json;charset=UTF-8
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sat, 28 Sep 2019 19:10:39 GMT
Date: Sat, 28 Sep 2019 19:10:39 GMT

< 
{
  "name" : "Albana",
  "color" : "white",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/api/vines/6"
    },
    "vines" : {
      "href" : "http://127.0.0.1:9090/api/vines"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## DELETE example:
```
$ curl -i -v -X DELETE http://127.0.0.1:9090/api/vines/6
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> DELETE /api/vines/6 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 204 
HTTP/1.1 204 
< Date: Sat, 28 Sep 2019 19:12:20 GMT
Date: Sat, 28 Sep 2019 19:12:20 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
```
