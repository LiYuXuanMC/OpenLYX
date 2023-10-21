#ifndef ANIMATION_H
#define ANIMATION_H

#include <chrono>
#include <cmath>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

class TweenUtil {
public:
    static float linear(float t, float b, float c, float d) {
        return c * t / d + b;
    }

    static float easeIn(float t, float b, float c, float d) {
        return c * powf(t / d, 2) + b;
    }

    static float easeOut(float t, float b, float c, float d) {
        return -c * (t /= d) * (t - 2) + b;
    }

    static float easeInOut(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * powf(t, 2) + b;
        return -c / 2 * ((--t) * (t - 2) - 1) + b;
    }

    static float backIn(float t, float b, float c, float d) {
        float s = 1.70158f;
        return c * (t /= d) * t * ((s + 1) * t - s) + b;
    }

    static float backOut(float t, float b, float c, float d) {
        float s = 1.70158f;
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }

    static float backInOut(float t, float b, float c, float d) {
        float s = 1.70158f;
        if ((t /= d / 2) < 1) return c / 2 * (t * t * (((s *= (1.525f)) + 1) * t - s)) + b;
        float postFix = t -= 2;
        return c / 2 * ((postFix)*t * (((s *= (1.525f)) + 1) * t + s) + 2) + b;
    }

    static float bounceOut(float t, float b, float c, float d) {
        if ((t /= d) < (1 / 2.75f)) {
            return c * (7.5625f * t * t) + b;
        }
        else if (t < (2 / 2.75f)) {
            float postFix = t -= (1.5f / 2.75f);
            return c * (7.5625f * (postFix)*t + .75f) + b;
        }
        else if (t < (2.5 / 2.75)) {
            float postFix = t -= (2.25f / 2.75f);
            return c * (7.5625f * (postFix)*t + .9375f) + b;
        }
        else {
            float postFix = t -= (2.625f / 2.75f);
            return c * (7.5625f * (postFix)*t + .984375f) + b;
        }
    }

    static float bounceIn(float t, float b, float c, float d) {
        return c - bounceOut(d - t, 0, c, d) + b;
    }

    static float bounceInOut(float t, float b, float c, float d) {
        if (t < d / 2) return bounceIn(t * 2, 0, c, d) * .5f + b;
        else return bounceOut(t * 2 - d, 0, c, d) * .5f + c * .5f + b;
    }

    static float elasticIn(float t, float b, float c, float d, float a = 0, float p = 0) {
        float s;
        if (t == 0) return b;
        if ((t /= d) == 1) return b + c;
        if (p == 0) p = d * .3f;
        if (a == 0 || a < abs(c)) {
            a = c;
            s = p / 4;
        }
        else {
            s = p / (2 * M_PI) * asinf(c / a);
        }
        return - (a * powf(2, 10 * (t -= 1)) * sinf((t * d - s) * (2 * M_PI) / p)) + b;
    }

    static float elasticOut(float t, float b, float c, float d, float a = 0, float p = 0) {
        float s;
        if (t == 0) return b;
        if ((t /= d) == 1) return b + c;
        if (p == 0) p = d * .3f;
        if (a == 0 || a < abs(c)) {
            a = c;
            s = p / 4;
        }
        else {
            s = p / (2 * M_PI) * asinf(c / a);
        }
        return (a * powf(2, -10 * t) * sinf((t * d - s) * (2 * M_PI) / p) + c + b);
    }

    static float elasticInOut(float t, float b, float c, float d, float a = 0, float p = 0) {
        float s;
        if (t == 0) return b;
        if ((t /= d / 2) == 2) return b + c;
        if (p == 0) p = d * (.3f * 1.5f);
        if (a == 0 || a < abs(c)) {
            a = c;
            s = p / 4;
        }
        else {
            s = p / (2 * M_PI) * asinf(c / a);
        }
        if (t < 1) {
            return -.5f * (a * powf(2, 10 * (t -= 1)) * sinf((t * d - s) * (2 * M_PI) / p)) + b;
        }
        else {
            return a * powf(2, -10 * (t -= 1)) * sinf((t * d - s) * (2 * M_PI) / p) * .5f + c + b;
        }
    }

    static float evaluate(float t, float start, float end, float duration, std::string easing) {
        float distance = end - start;

        if (easing == "linear") {
            return linear(t, start, distance, duration);
        }
        else if (easing == "easeIn") {
            return easeIn(t, start, distance, duration);
        }
        else if (easing == "easeOut") {
            return easeOut(t, start, distance, duration);
        }
        else if (easing == "easeInOut") {
            return easeInOut(t, start, distance, duration);
        }
        else if (easing == "backIn") {
            return backIn(t, start, distance, duration);
        }
        else if (easing == "backOut") {
            return backOut(t, start, distance, duration);
        }
        else if (easing == "backInOut") {
            return backInOut(t, start, distance, duration);
        }
        else if (easing == "bounceIn") {
            return bounceIn(t, start, distance, duration);
        }
        else if (easing == "bounceOut") {
            return bounceOut(t, start, distance, duration);
        }
        else if (easing == "bounceInOut") {
            return bounceInOut(t, start, distance, duration);
        }
        else if (easing == "elasticIn") {
            return elasticIn(t, start, distance, duration);
        }
        else if (easing == "elasticOut") {
            return elasticOut(t, start, distance, duration);
        }
        else if (easing == "elasticInOut") {
            return elasticInOut(t, start, distance, duration);
        }
        else {
            // 默认使用线性缓动
            return linear(t, start, distance, duration);
        }
    }
};


class Animation {
public:
    Animation(float begin, float end, float duration, float (*ease)(float, float, float, float),float time=0, float value = 0)
        : begin(begin), end(end), duration(duration), ease(ease), time(time), value(value) {}

    float update(float deltaTime,bool up = true){
        if (up) {
            time += deltaTime;
        }
        else {
            time -= deltaTime;
        }
        if (time > duration) {
            time = duration;
        }
        else if (time < 0) {
            time = 0;
        }
        value = ease(time, begin, end - begin, duration);
        return value;
    }

    void setTarget(float target) {
        begin = target;
    }

    float getValue() {
        return value;
    }

    void reset() {
        this->time = 0;
    }

    void setBegin(float begin) {
        this->begin = begin;
    }

    float getBegin() {
        return this->begin;
    }

    float getEnd() {
        return this->end;
    }

    void setEnd(float end) {
        this->end = end;
    }

    void setTime(float time) {
        this->time = time;
    }

    void setValue(float fValue) {
        value = fValue;
    }

    bool isFinished() {
        return time >= duration;
    }

private:
    float begin;
    float end;
    float duration;
    float (*ease)(float, float, float, float);
    float time;
    float value;
};

#endif