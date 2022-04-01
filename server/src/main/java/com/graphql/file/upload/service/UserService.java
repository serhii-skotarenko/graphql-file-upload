package com.graphql.file.upload.service;

import com.graphql.file.upload.models.inputs.UserInput;
import com.graphql.file.upload.models.types.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    private static final String DEFAULT_AVATAR = "data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAABmJLR0QA/wD/AP+gvaeTAAADVklEQVRoge2ZTUgVURTHfz7Nj0I0JcSigr7ITAzMMqJaVUK7FgVFLapVRYWbaFe0DloVEZSbICLTdpUFpRhuhApDigrCrBDzI7LS1GlxzjRm+Zy59466eD94HN579/zv/8zMnTtzL6RIEQtpMWqvADYCq4B8YBToAVqBJmAkxr6tSQcOAe2Al+TzBtjqsmOXZ6QYqAM26fePQAvQBXTqb0uBauQs/QCuAzeApw59WJEPvEKO9mtgF5MfpARwgb/P0E1gbvw2p6YWMdQG5IVonwDWAKeAbs29E5u7kCxBBu4voNwgfxnQixRT7dBXZI6qiToLjbMEl9iMUa8mDltolKjGWyeODGlVE1UWGunAT2AMw0GfsOjcp1Bjv4XGKDCE3OkyTQRcFOLP0J6lTobGMZNkF4V0ayyy0ChALqlB4KuJgItCPmlcaKGxUuM7UwEXhbzQaDKH+GzR2GIq4KKQNo0bLDS2aWw2FXBRiK9RSTBgo5BJcEYGHPgx5jFyxzqN2dN0GnBGNR469BWZDjVhM0bKVeOlqYCLS6tdY4WFRqVG40JccBA5mu+BGoP8Gs31gAMOfUUmHbiNzMgDRDvLCc3xn57Tnbsz4BliqDRCTpnmdNh27mKM+DzRuD9Czr4JubOCCuTo9gILQrQvAL5ozuYYfRnRgBh7TPB4/z9ygfvatnEafEVmEfCZqV+0VmubfmC5i45djhGQNayuCP324Oj11nUhEO4Nz38Jy3LVaRyFZGv8lqTNoEZnhbimCCnAAxYnaZeHTKBDyLrYrKIMeI4UcS9E+1sEk+H6GH2FphS4DAwTrP0Wh8grJFi1HwGuAeti8jgpGcBu4BFyiXjIkk4tsqAdllzgqub6C9rNwB5gjkO//5ANHCd4WvWQgXsFWGuhWwJcIhhfHvABOAnkWOj+QxqygdM1rqN24AQw32E/ecAxgrHmIfssR3CwlzMPuDtOuJXpWTXfjmwA+f02qBcjspD9Pg+Zhfc6MBiVAwSXXBOGS6oXVaAT2S6bKcrUg4fsdkViJbJ5M4rszs40VYiXYWTHODTnkSPwIAZTpjQins5N/CPZs9YOjfVxODLE32fcOfGPZLe0PqJNbtNJH/KG+YfJCskBvsdux44cZJcrRYoUIfgNHq3EbO8epBkAAAAASUVORK5CYII=";
    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public User createUser(UserInput request) {
        var user = new User(
                users.size() + 1,
                request.getName(),
                request.getEmail(),
                getAvatar(request.getAvatar())
        );
        users.add(user);
        return user;
    }

    private static String getAvatar(byte[] avatarBytes) {
        if (avatarBytes != null && avatarBytes.length > 0) {
            return "data:image/png;base64, %s".formatted(Base64.getEncoder().encodeToString(avatarBytes));
        } else {
            return DEFAULT_AVATAR;
        }
    }
}
